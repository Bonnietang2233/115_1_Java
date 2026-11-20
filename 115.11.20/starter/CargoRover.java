// 類別：CargoRover（貨運探測車，第 7 週課後獨立遷移路線 A）
// 意義：一種探測車（is-a Rover），多了一個容量 3 的貨艙。
// 作用：沿用 Rover 的能源、樣本、行動規則；新增 load 裝貨。
//       貨物不是科學樣本：裝貨不耗能源、不加樣本、不加行動次數。
public class CargoRover extends Rover {
    // CAPACITY：貨艙容量，固定 3。
    public static final int CAPACITY = 3;
    // cargo：目前貨物數量，初始 0。
    private int cargo;

    // 建構子：能源與樣本交給 Rover 保存，貨物從 0 開始。
    public CargoRover(int energy, int samples) {
        super(energy, samples);
        cargo = 0;
    }

    // getCargo：回傳目前貨物數量。
    public int getCargo() { return cargo; }

    // load：裝入 amount 件貨物。
    // 已停止 → 任務已停止；amount <= 0 或超過剩餘貨位 → 已拒絕；其餘 → 成功。
    // 被拒絕時 cargo 不變。用 amount > CAPACITY - cargo 判斷，避免先相加再判斷。
    public String load(int amount) {
        if (isStopped()) { return "任務已停止"; }
        if (amount <= 0 || amount > CAPACITY - cargo) { return "已拒絕"; }
        cargo += amount;
        return "成功";
    }
}
