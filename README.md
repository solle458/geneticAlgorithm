# geneticAlgorithm
学校の授業で作成した遺伝的アルゴリズムのコードです。
このプログラムはカーレースをシミュレーションするものであり、車を適切に操作しランダムに出現する旗を時間内にできるだけ多くとるものです。
時間が足りなかったため車の操縦ロジックを最低限に抑え、それらに必要なパラメータを遺伝的アルゴリズムで最適化しました。

```
cd /carrace/carrace
javac -cp . simplerace/x/*.java
java -cp . simplerace.x.GA//パラメータの学習
java simplerace.Play simplerace.x.AIController//シミュレーションの実行
java simplerace.Stats simplerace.x.AIController//10000回シミュレーションした結果を出力
```
