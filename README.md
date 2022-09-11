# library-ui
Ui of LibraryApplication

# 注意
- アプリを実行する前にやること
    - application.ymlの作成

## ポートが開いてない時
```shell
sudo lsof -P -i:8081
sudo kill 番号
```

## 実行
### 実行可能jarファイルの作成
```
./mvnw clean package
テスト失敗時
./mvnw clean package -DskipTests=true
```
### jarファイルの実行
```
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar target/library-api-0.0.1-SNAPSHOT.jar
```