with cbe as (SELECT sum(lot_qty) td_qty,
                    account,
                    symbol
             FROM (SELECT x.*,
                          row_number() over (partition BY account,
                              symbol,
                              unique_opening_record_id,
                              --unique_closing_record_id,
                              trade_no,
                              related_trd_no
                              ORDER BY
                                  maxit_trsn_id desc nulls last) rn
                   FROM STG_POSTTRADE.REFINITIV_CBE_DB x
                   WHERE file_name='DRW_VM_PLUTO_MASTER_dat_20240511' AND
                       partner = 'CASH')
             WHERE rn = 1
               AND lot_status = 'O'
               AND update_type <> 'D'
             GROUP BY account,
                      symbol)
   , filters as
    (SELECT y.account                                                iaccount,
            y.symbol                                                 isymbol,
            x.ACCT_NO                                                accountno,
            cast(nvl(round(y.td_qty,
                           8),
                     0) as number(28,
                8)) - cast(nvl(round(x.tclose,
                                     8),
                               0) as number(28,
                8))                                                  break_qty,
            case when break_qty <> 0 then 'UNMATCH' else 'MATCH' end break_type
     FROM stg_icdb.pos_bal x
              JOIN cbe y
                   ON
                       trim(x.corr) || trim(x.office) || trim(x.acct_no) = y.account AND
                       x.symbol = y.symbol
     WHERE x.system_dt = '2024-05-11'
       AND corr = 'CASH')
SELECT x.account_no                    as account
     , x.partner                       as partner
     , x.symbol                        as symbol
     , (case
            when
                x.account_type_ind = 1
                then 'C'
            else 'M'
    end)                               as acct_type
     , x.lot_qty                       as lot_qty
     , x.original_cost                 as original_cost
     , x.original_prc                  as original_prc
     , x.adjusted_cost                 as adjusted_cost
     , x.acqstn_dt                     as trade_date
     , x.settlement_dt                 as settlement_dt
     , x.transfer_initiated_dt         as entry_date
     , x.trade_no                      as trade_no
     , x.iso_currency_cd               as iso_currency_cd
     , x.maxit_trsn_id                 as maxit_trsn_id
     , x.original_trns_type            as trd_type
     , 'REFINITIV'                     as src, row_number() over (partition BY account,
    symbol,
    unique_opening_record_id,
    --unique_closing_record_id,
    trade_no,
    related_trd_no
    ORDER BY
        maxit_trsn_id desc nulls last) rn
FROM STG_POSTTRADE.REFINITIV_CBE_DB x
WHERE  file_name='DRW_VM_PLUTO_MASTER_dat_20240511' and
    (account, symbol) IN (select iaccount, isymbol from filters where break_type = 'MATCH')
UNION ALL
SELECT account,PARTNER,symbol,acct_type,lot_qty,original_cost,original_prc,adjusted_cost,trade_date,settlement_dt,
       entry_date, trade_no,iso_currency_cd, maxit_trsn_id,trd_type,src,rn FROM (SELECT dpos.ACCOUNTNO                    as account,
                                                                                        p.WLPID                           as PARTNER,
                                                                                        ins.symbol                        as symbol,
                                                                                        (case
                                                                                             when
                                                                                                 dpos.ACCOUNTTYPE = 1
                                                                                                 then 'C'
                                                                                             else 'M'
                                                                                            end)                          as acct_type,
                                                                                        dpos.OPENQTY                      as lot_qty,
                                                                                        (dpos.OPENQTY *  dpos.INITPX)     as original_cost,
                                                                                        dpos.INITPX                       as original_prc,
                                                                                        dpos.COSTBASIS                    as adjusted_cost,
                                                                                        dpos.CREATEDWHEN                  as trade_date,
                                                                                        dpos.CREATEDWHEN                  as settlement_dt,
                                                                                        dpos.CREATEDWHEN                  as entry_date,
                                                                                        '0'                               as trade_no,
                                                                                        'USD'                             as iso_currency_cd,
                                                                                        'TAXLOTID'                        as maxit_trsn_id,
                                                                                        'BUY'                             as trd_type,
                                                                                        'DYNAMO'                          as src,
                                                                                        dpos.eventtype,
                                                                                        row_number() over (partition BY dpos.accountid,
                                                                                            dpos.INSTRUMENTID,
                                                                                            dpos.ACCOUNTTYPE
                                                                                            ORDER BY
                                                                                                EVENT_TS desc nulls last) rn
                                                                                 from stg_dynamodb.trade_positions dpos
                                                                                          join stg_dynamodb.REF_INSTRUMENTS ins on ins.INSTRUMENTID = dpos.INSTRUMENT_ID
                                                                                          join stg_dynamodb.bo_accounts a on dpos.accountid = a.accountid
                                                                                          join stg_dynamodb.bo_wlps p on p.IBID = a.IBID
                                                                                 where (dpos.accountno, ins.symbol) IN (select accountno, isymbol from filters where break_type = 'UNMATCH')
                                                                                   and EVENT_TS < '2024-05-11 23:59:59.000') where rn=1 and eventtype <>'REMOVE'