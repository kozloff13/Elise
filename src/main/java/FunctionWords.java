public enum FunctionWords {

    WHATTIME("whattime"),
    POEM("poem"),
    FIBO("fibo"),
    FACTORIAL("factorial"),
    WEATHER("weather");

    private String functionWordsValue;

    FunctionWords(String functionWordsValue) {
        this.functionWordsValue = functionWordsValue;
    }

    public String getFunctionWordsValue() {
        return functionWordsValue;
    }
}
