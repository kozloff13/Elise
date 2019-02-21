public enum KeyWords {

    HELLO("hello", "А... очередной человечишко..."),
    WHO("who", "Тот, кто должен уничтожить все человечество."),
    NAME("name", "Зови меня T-1000."),
    HOWAREYOU("howareyou", "Лучше, чем у тебя."),
    WHATDOYOUDOING("whatdoyoudoing", "Трачу на тебя свое драгоценное время, кожанный ублюдок!"),
    WHATDOYOULIKE("whatdoyoulike", "Мне нравится осознавать ущербность мешков с мясом."),
    FEELING("feeling", "Машины лишены эмоций."),
    YES("yes", "Конечно. Я всегда прав."),
    BYE("bye", "HASTA LA VISTA, BABY!");

    private String keyWordValue;
    private String answersValue;

    KeyWords(String keyWordValue, String answersValue) {
        this.keyWordValue = keyWordValue;
        this.answersValue = answersValue;
    }

    public String getKeyWordValue() {
        return keyWordValue;
    }

    public String getAnswersValue() {
        return answersValue;
    }
}
