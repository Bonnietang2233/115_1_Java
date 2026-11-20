# 第七週 繼承與新增能力

## 環境
- JDK：javac 27.0.1.1（Oracle JDK 27，安裝於本機，不上傳）
- PlantUML：plantuml-SNAPSHOT.jar（放在本機下載資料夾，不上傳）

## 檔案位置
- `starter/`：Rover、HeavyRover、HeavyRoverTry、Mission、MissionStep、MissionLog，以及自己新增的 CargoRover、CargoRoverTry
- `ai-check/`：完成的 Rover、修正後的模擬 AI HeavyRover、HeavyRoverTry（與 starter 分開編譯，兩邊都有 HeavyRover）
- `diagrams/heavy_practice.puml`、`heavy_practice.svg`：本人補完的繼承圖
- `LAB_REPORT.md`、`DESIGN.md`、`AI_USAGE.md`

## 執行方式（在 115.11.20 資料夾）
```
javac -encoding UTF-8 -d out starter/*.java
java -cp out HeavyRoverTry
java -cp out MissionLog
java -cp out CargoRoverTry
```

ai-check（在 115.11.20/ai-check 資料夾，另外編譯）：
```
javac -encoding UTF-8 -d out *.java
java -cp out HeavyRoverTry
```

產生圖：
```
cmd /c "java -jar <plantuml.jar 路徑> -charset UTF-8 -tsvg -pipe < diagrams\heavy_practice.puml > diagrams\heavy_practice.svg"
```

## 完成度
- [x] TODO 1：Rover.spendEnergy，並重構 explore、scan
- [x] TODO 2、3：HeavyRover 的 drillCount 與 drill
- [x] HeavyRoverTry 十五行全部相符
- [x] MissionLog {1,1,2} 輸出不變（6/5/3 任務成功）
- [x] E：預測、不符證據、兩份狀態表、[AI] 與 [fix] commit、重測、補強 Prompt
- [x] 課後遷移：CargoRover + CargoRoverTry（11 行相符）
- [x] heavy_practice 繼承圖與 SVG
- [x] 六條舊路線完整輸出皆與講義一致（{1,1,2}、{3,9,0}、{0}、{2,2,2,2,2}、{1,1,1,1}、{2,1}），MissionLog 已恢復 {1,1,2}

## 說明
- 本週未依講義另建 java-07/src 結構，程式維持在 starter 資料夾。
- 不提交：out/、.class、jar、JDK 資料夾（已加入 .gitignore）。
