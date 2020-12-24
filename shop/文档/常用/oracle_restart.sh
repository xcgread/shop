su - oracle -c "sqlplus /nolog<< EOF
conn /as sysdba
shutdown immediate
startup
quit
EOF"

