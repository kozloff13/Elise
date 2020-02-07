import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;
/**
 * created by kozloff 3.05.2018
 *
 * Основной класс программы
 */

class Controller {

    //Массивы с ответами в обычном режиме (чекбокс не включен)
    //их судьба под вопросом
    private final String[] COMMON_PHRASES = {
            "Нет ничего ценне слов, сказанных к месту и ко времени.",
            "Порой молчание может сказать больше, нежели уйма слов.",
            "Перед тем, как писать/говорить всегда лучше подумать.",
            "Вежливая и грамотная речь говорит о наличии души.",
            "Пошел в жопу!",
            "Fuck you asshole!",
            "Кто ясно мыслит, тот ясно излагает.",
            "Боюсь, вы что-то не договариваете.",
            "Записывая слова, мы удваиваем их силу.",
            "Слова могут ранить, но могут и исцелять"
    };

    private final String[] ELUSIVE_ANSWERS = {
            "Вопрос непростой, мне нужно его обдумать...",
            "Не уверен, что располагаю такой информацией.",
            "Может лучше поговорим о чем-то другом?",
            "Простите, но это очень личный вопрос.",
            "Не уверен, что вам понравится ответ.",
            "Поверьте, я сам хотел бы это знать.",
            "Вы действительно хотите это знать?",
            "Не дави на меня!",
            "Че пацаны, аниме?",
            "Давайте сохраним интригу?"
    };

    private final Map<String, String> PATTERNS_FOR_ANALYSIS = new HashMap<String, String>() {
        {
            //hello
            put("хай", KeyWords.HELLO.getKeyWordValue());
            put("привет", KeyWords.HELLO.getKeyWordValue());
            put("дороу", KeyWords.HELLO.getKeyWordValue());
            put("дратути", KeyWords.HELLO.getKeyWordValue());
            //who
            put("кто\\s.*ты", KeyWords.WHO.getKeyWordValue());
            put("ты\\s.*кто", KeyWords.WHO.getKeyWordValue());
            //name
            put("как\\s.*зовут", KeyWords.NAME.getKeyWordValue());
            put("как\\s.*имя", KeyWords.NAME.getKeyWordValue());
            put("есть\\s.*имя", KeyWords.NAME.getKeyWordValue());
            put("какое\\s.*имя", KeyWords.NAME.getKeyWordValue());
            //howareyou
            put("как\\s.*дела", KeyWords.HOWAREYOU.getKeyWordValue());
            put("какие\\s.*дела", KeyWords.HOWAREYOU.getKeyWordValue());
            put("как\\s.*жизнь", KeyWords.HOWAREYOU.getKeyWordValue());
            //whatdoyoudoing
            put("зачем\\s.*тут", KeyWords.WHATDOYOUDOING.getKeyWordValue());
            put("зачем\\s.*здесь", KeyWords.WHATDOYOUDOING.getKeyWordValue());
            put("что\\s.*делаешь", KeyWords.WHATDOYOUDOING.getKeyWordValue());
            put("чем\\s.*занимаешься", KeyWords.WHATDOYOUDOING.getKeyWordValue());
            //whatdoyoulike
            put("что\\s.*нравится", KeyWords.WHATDOYOULIKE.getKeyWordValue());
            put("что\\s.*любишь", KeyWords.WHATDOYOULIKE.getKeyWordValue());
            //iamfeeling
            put("кажется", KeyWords.FEELING.getKeyWordValue());
            put("чувствую", KeyWords.FEELING.getKeyWordValue());
            put("испытываю", KeyWords.FEELING.getKeyWordValue());
            put("чувствуешь", KeyWords.FEELING.getKeyWordValue());
            put("эмоции", KeyWords.FEELING.getKeyWordValue());
            put("чувства", KeyWords.FEELING.getKeyWordValue());
            //yes
            put("да", KeyWords.YES.getKeyWordValue());
            put("согласен", KeyWords.YES.getKeyWordValue());
            //bye
            put("до\\s.*свидания", KeyWords.BYE.getKeyWordValue());
            put("прощай", KeyWords.BYE.getKeyWordValue());
            put("увидимся", KeyWords.BYE.getKeyWordValue());
            //whattime
            put("который\\s.*час", FunctionWords.WHATTIME.getFunctionWordsValue());
            put("сколько\\s.*время", FunctionWords.WHATTIME.getFunctionWordsValue());
            //poem
            put("расскажи\\s.*стих", FunctionWords.POEM.getFunctionWordsValue());
            put("прочитай\\s.*стихотворение", FunctionWords.POEM.getFunctionWordsValue());
            //fibo
            put("посчитай\\s.*фибоначчи\\s.\\d.", FunctionWords.FIBO.getFunctionWordsValue());
            //factorial
            put("посчитай\\s.*факториал\\s.\\d.", FunctionWords.FACTORIAL.getFunctionWordsValue());
            //weather
            put("какая\\s.*погода", FunctionWords.WEATHER.getFunctionWordsValue());
        }
    };

    private final Map<String, String> ANSWERS_BY_PATTERNS = new HashMap<String, String>() {
        {
            put(KeyWords.HELLO.getKeyWordValue(), KeyWords.HELLO.getAnswersValue());
            put(KeyWords.WHO.getKeyWordValue(), KeyWords.WHO.getAnswersValue());
            put(KeyWords.NAME.getKeyWordValue(), KeyWords.NAME.getAnswersValue());
            put(KeyWords.HOWAREYOU.getKeyWordValue(), KeyWords.HOWAREYOU.getAnswersValue());
            put(KeyWords.WHATDOYOUDOING.getKeyWordValue(), KeyWords.WHATDOYOUDOING.getAnswersValue());
            put(KeyWords.WHATDOYOULIKE.getKeyWordValue(), KeyWords.WHATDOYOULIKE.getAnswersValue());
            put(KeyWords.FEELING.getKeyWordValue(), KeyWords.FEELING.getAnswersValue());
            put(KeyWords.YES.getKeyWordValue(), KeyWords.YES.getAnswersValue());
            put(KeyWords.BYE.getKeyWordValue(), KeyWords.BYE.getAnswersValue());
        }
    };

    private Random random;
    private Date date;
    private Poem poem = new Poem();
    private String[] poemString = poem.poemString();
    private Math math = new Math();
    private Weather wth = new Weather();

    Controller() {
        random = new Random();
        date = new Date();
    }

    String sayInReturn(String msg, boolean ai) {
        String say = (msg.trim().endsWith("?")) ?
                ELUSIVE_ANSWERS[random.nextInt(ELUSIVE_ANSWERS.length)] :
                COMMON_PHRASES[random.nextInt(COMMON_PHRASES.length)];
        if (ai) {
            String message = String.join(" ", msg.toLowerCase().split(" [ {,|.}?+]"));
            for (Map.Entry<String, String> o : PATTERNS_FOR_ANALYSIS.entrySet()) {
                Pattern pattern = Pattern.compile(o.getKey());
                if (pattern.matcher(message).find()) {
                    if (o.getValue().equals(FunctionWords.WHATTIME.getFunctionWordsValue())) {
                        return date.toString();
                    } else if (o.getValue().equals(FunctionWords.POEM.getFunctionWordsValue())) {
                        return poemString[random.nextInt(poemString.length)];
                    } else if (o.getValue().equals(FunctionWords.FIBO.getFunctionWordsValue())) {
                        return String.valueOf(math.fibo(getInNum(msg)));
                    } else if (o.getValue().equals(FunctionWords.FACTORIAL.getFunctionWordsValue())) {
                        return String.valueOf(math.factorial(getInNum(msg)));
                    } else if (o.getValue().equals(FunctionWords.WEATHER.getFunctionWordsValue())) {
                        return wth.weatherToday();
                    } else return ANSWERS_BY_PATTERNS.get(o.getValue());
                }
            }
        }
        return say;
    }

    private int getInNum(String msg) {
        String numberFromMessage;
        numberFromMessage = msg.replaceAll("\\D+", "");
        return Integer.parseInt(numberFromMessage);
    }
}