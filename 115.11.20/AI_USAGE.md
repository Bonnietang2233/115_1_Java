# 第七週 AI 使用紀錄

姓名／學號：待填
工具及來源：真實 AI：Claude（Anthropic）；E 段為教師模擬回覆（ai/HeavyRover.java），未呼叫真實 AI。

## 精簡紀錄

| 使用目的與Prompt摘要（註明來源） | 採納／拒絕與理由 | 本人驗證：預期、實際、證據位置 | 最終修改與commit |
|---|---|---|---|
| 【真實 AI：Claude】請 AI 協助完成 TODO 1–3：Rover.spendEnergy、用它重構 explore／scan；HeavyRover 新增 drillCount 與 drill | 採納：spendEnergy 先檢查 amount <= 0 或超過能源才拒絕，拒絕時不扣；drill 先檢查 isStopped（drill 不經過 execute），再 spendEnergy，成功才 addSamples、countAction、drillCount++；不重複宣告 energy、samples | 預期 HeavyRoverTry 十五行相符、六條舊路線輸出不變；實際皆相符（MissionLog {1,1,2} 6/5/3 任務成功、{3,9,0} 20/0/0 已返回等）。證據：LAB_REPORT D5 | starter/Rover.java、HeavyRover.java；commit「week7 TODO 1-3」 |
| 【教師模擬回覆】ai/HeavyRover.java：父類別欄位 private，所以子類別重新宣告 energy、samples 再更新 | 拒絕：重複宣告造成一個物件裡兩份狀態，getter 讀 Rover 那份，drill 改子類別那份；另缺停止檢查與 countAction | 本人預測：能編譯、第一次 drill 後 getEnergy() 為 12。實際：能編譯，但 getEnergy() 為 20，鑽探後 20/0/0/1，共 9 行不符。證據：LAB_REPORT E1、E2、E3 | 刪重複欄位與建構子設定，drill 改用 isStopped、spendEnergy、addSamples、countAction，重測十五行相符。commit「[AI] simulated HeavyRover original in ai-check」→「[fix] remove duplicate state in AI HeavyRover」 |
| 【真實 AI：Claude】請 AI 協助課後遷移 CargoRover 與測試入口 CargoRoverTry | 採納：load 不耗能源、不加樣本、不加行動次數；用 amount > CAPACITY - cargo 判斷，被拒絕時 cargo 不變 | 預期依講義規格與自補案例（負數、一次裝滿、滿載後再裝、裝貨後探索正常）；實際 11 行相符。證據：LAB_REPORT 課後獨立遷移 | starter/CargoRover.java、CargoRoverTry.java；commit「week7 CargoRover transfer」 |
| 【真實 AI：Claude】請 AI 協助補完 heavy_practice.puml，並排除產圖問題 | 採納：補三個 # 方法、HeavyRover 常數／欄位／建構子／兩個方法、繼承線；不畫 Mission→HeavyRover | 第一次產圖時 puml 尚未存檔，SVG 仍是起始圖；換檔後重產，確認三角形指向 Rover、# 方法與中文註解正常 | diagrams/heavy_practice.puml、.svg |

另外：安裝 JDK 時 JDK 被裝進 starter 資料夾，第一次 push 因單檔超過 100 MB 被 GitHub 拒絕；依 AI 建議以 git reset --soft 取消 commit、git rm --cached 移除，並加入 .gitignore 後重新 push。

真實AI對話另列一行，寫工具與採納理由，不把模擬回覆當成真實對話。記錄可引用LAB_REPORT的完整輸出，不重抄十五行。不得上傳個資、帳號密碼或未授權程式。

## 反思

- 哪個案例讓我發現「回成功卻沒更新正確狀態」：HeavyRoverTry 的「鑽探後」。AI 版 drill 回傳「成功」，但四個 getter 讀到 20/0/0/1，能源、樣本、行動次數都沒變。只看回傳值會以為正確，要查 getter 才發現 drill 改的是子類別另一份 energy。另一個是「能源耗盡後無法充電」：8 格的車鑽探後應該耗盡，卻還能充電成功。
- 我保留或拒絕AI建議的理由：拒絕模擬 AI「子類別重新宣告一份欄位」的做法，因為 private 不被繼承不等於物件沒有那份資料，重複宣告只會多出第二份狀態。採納 Claude 建議改用 protected 方法，因為扣能源規則只寫在 spendEnergy 一處，getter 和 drill 改的是同一份。
- 我仍無法解釋或尚未完成的部分：六條舊路線已在本週程式重跑完成，輸出與講義一致（見 LAB_REPORT D5）。為什麼 protected 在 Java 同套件的其他類別也能呼叫，還需要再練習 C3 實驗確認；本週程式沒有依講義另建 java-07/src 結構。
