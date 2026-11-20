// 類別：CargoRoverTry（CargoRover 的測試入口）
// 作用：依講義規格逐項比對「實際」與「預期」，不同時印「不符」。
public class CargoRoverTry {
    public static void main(String[] args) {
        CargoRover c = new CargoRover(20, 0);
        check("裝 2 件", c.load(2) + "/" + c.getCargo(), "成功/2");
        check("再裝 2 件（超過容量）", c.load(2) + "/" + c.getCargo(), "已拒絕/2");
        check("再裝 1 件（剛好滿載）", c.load(1) + "/" + c.getCargo(), "成功/3");
        check("裝 0 件", c.load(0) + "/" + c.getCargo(), "已拒絕/3");
        check("原有三個 getter", c.getEnergy() + "/" + c.getSamples() + "/" + c.getActionCount(), "20/0/0");

        CargoRover empty = new CargoRover(0, 0);
        check("能源耗盡時裝貨", empty.load(1) + "/" + empty.getCargo(), "任務已停止/0");
        CargoRover done = new CargoRover(20, 5);
        check("已達標時裝貨", done.load(1) + "/" + done.getCargo(), "任務已停止/0");

        // 自訂補充案例
        CargoRover neg = new CargoRover(20, 0);
        check("負數件數", neg.load(-1) + "/" + neg.getCargo(), "已拒絕/0");
        CargoRover full = new CargoRover(20, 0);
        check("一次裝滿 3 件", full.load(3) + "/" + full.getCargo(), "成功/3");
        check("滿載後再裝 1 件", full.load(1) + "/" + full.getCargo(), "已拒絕/3");
        check("裝貨後探索仍正常", full.execute(1) + "/" + full.getEnergy() + "/" + full.getSamples()
                + "/" + full.getActionCount() + "/" + full.getCargo(), "成功/14/2/1/3");
    }

    static void check(String label, String actual, String expected) {
        String mark = actual.equals(expected) ? "相符" : "不符";
        System.out.println(label + ": " + actual + " （預期 " + expected + "） " + mark);
    }
}
