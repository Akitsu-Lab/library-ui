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
```shell
権限変更
chmod 744 mvnw
```
```shell
jarファイル作成
./mvnw clean package
```
```shell
テスト通さずjarファイル作成
./mvnw clean package -DskipTests=true
```
### jarファイルの実行
```shell
java -jar target/library-api-0.0.1-SNAPSHOT.jar
```
### リモートデバッグ
```shell
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar target/library-api-0.0.1-SNAPSHOT.jar
```
---
javaコマンド

| オプション          | 説明                                                                           |
|----------------|------------------------------------------------------------------------------|
| -agentlib:jdwp | JDWPを利用するライブラリをロードします                                                        |
| transport      | 「dt_socket」でソケット通信を利用します                                                     |
| server         | 「y」を指定するとデバッガ空の接続を待ち受けます                                                     |
| suspend        | デバッガが接続できるまでアプリケーションの実行を停止する場合、<br/>「y」を指定します。アプリケーションの起動時の処理をデバッグする場合に利用します |
| address        | デバッガからの接続を待ち受けるポート番号を指定します                                                   |

## デプロイ
### ファイルの準備
- library-ui-0.0.1-SNAPSHOT.jar
- jar_controller.sh
### 準備したファイルをサーバに送る
- 上記ファイルは同一のディレクトリ(ホームディレクトリなど)に配置
### サーバ上でjar_controller.shの権限を書き換える
```shell
chmod 755 jar_controller.sh
```
### アプリの起動
```shell
./jar_controller.sh on ui
```
### アプリの停止
```shell
./jar_controller.sh off ui
```

### 動作確認
```http request
http://localhost:8081/ui/books/list
```
```http request
http://os3-285-32121.vs.sakura.ne.jp:8081/ui/books/list
```
