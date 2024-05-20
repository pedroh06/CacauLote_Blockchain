# cacauLote_BLockchain

//para entrar na vm
ssh -i cacau_lote.pem azureuser@40.114.43.74

//para entrar no banco de dados
mysql -h cacau-lote-db.mysql.database.azure.com -u user -p

//para rodar o programa
mvn exec:java -Dexec.mainClass="com.example.App"

obs:
- esteja em demo/ para rodar o mvn
- esteja em cacauLote_BLockchain/ para qualquer comando git
- caso o programa na vm não aceite git pull, faça um git reset --hard origin/main