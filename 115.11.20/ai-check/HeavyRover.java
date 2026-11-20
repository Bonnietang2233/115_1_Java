// 類別：HeavyRover（重型探測車，第 7 週版本）
// 意義：一種探測車（is-a Rover），所以用 extends Rover 繼承探索、掃描、充電與全部狀態。
// 作用：在 Rover 的基礎上新增「鑽探」能力；本週只新增方法，不覆寫任何既有方法。
public class HeavyRover extends Rover {
    // 已提供的具名常數，避免把成本和產量兩個數字搞混；不是本週新增語法驗收。
    // DRILL_COST：鑽探一次消耗的能源格數。
    public static final int DRILL_COST = 8;
    // DRILL_YIELD：鑽探一次取得的樣本份數。
    public static final int DRILL_YIELD = 3;

    // TODO 2：只新增鑽探次數，不再宣告 energy、samples、actionCount。
    // drillCount：成功鑽探的次數。
    private int drillCount;

    // 建構子：建立重型探測車；能源與樣本交給父類別 Rover 保存。
    public HeavyRover(int energy, int samples) {
        super(energy, samples);
        drillCount = 0;
    }

    // getDrillCount：回傳成功鑽探的次數。
    public int getDrillCount() { return drillCount; }

    // drill：鑽探一次，回傳結果字串（「成功」「能源不足」或「任務已停止」）。
    // 這是 HeavyRover 新增的公開入口，不經過 execute。
    // TODO 3：新公開入口自己檢查停止。先全部拒絕條件，再更新整組狀態。
    public String drill() {
        // 1. 已停止就拒絕，什麼都不改（drill 不經過 execute，要自己檢查）
        if (isStopped()) { return "任務已停止"; }
        // 2. 扣能源；不夠就拒絕，什麼都不改
        if (!spendEnergy(DRILL_COST)) { return "能源不足"; }
        // 3. 成功才更新：樣本、行動次數、鑽探次數
        addSamples(DRILL_YIELD);
        countAction();
        drillCount++;
        return "成功";
    }
}