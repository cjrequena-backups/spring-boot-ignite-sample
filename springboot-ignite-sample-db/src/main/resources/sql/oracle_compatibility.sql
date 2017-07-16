set database sql syntax ORA true;
set database transaction control MVCC;
set database transaction rollback on conflict true;
SET DATABASE DEFAULT ISOLATION LEVEL READ COMMITTED;