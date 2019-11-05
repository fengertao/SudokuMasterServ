package charlie.feng.game.sudokumasterserv.master;

public enum MsgKey {
    START_RESOLVE("Start resolving grid %s", "开始解决数独", 0),
    SUCCESS_RESOLVE("Resolved grid %s", "成功解决数独", 0),
    ABORT_RESOLVE("Abort resolving grid %s", "放弃解决数独", 0),
    GET_VALUE("Get value [%s].", "填入 [%s]", 1), //p1 is cell location, p2 is new value
    REMOVE_CANDIDATE("Removed [%s] remains: [%s].", "消除 [%s] 剩余 [%s].", 2), //p1 is cell location, p2 is new value, p3 is remain value

    VALUE_IN_SAME_ROW("Value exist in same row", "同行单元格已有该值", 3),
    VALUE_IN_SAME_COLUMN("Value exist in same column", "同列单元格已有该值", 3),
    VALUE_IN_SAME_BLOCK("Value exist in same block", "同块单元格已经该值", 3);

    private String enKey;
    private String cnKey;
    private int level;

    MsgKey(String enKey, String cnKey, int level) {
        this.enKey = enKey;
        this.cnKey = cnKey;
        this.level = level;
    }

    private String getEnKey() {
        return enKey;
    }

    private String getCnKey() {
        return cnKey;
    }

    public int getLevel() {
        return level;
    }


    public String getMsg(String lang, String... msgParams) {
        if (lang.startsWith("ZH")) {
            return String.format(getCnKey(), msgParams);
        } else {
            return String.format(getEnKey(), msgParams);
        }
    }
}
