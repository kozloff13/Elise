import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;

/**
 * created by kozloff 3.05.2018
 */

//Дополнительный класс - парсер сайта Яндекс.Погода.
//Выводит температуру и напрвление/скорость ветра (утро, день, вечер, ночь)
class Weather {

    //пока что простенько без излишеств... но немного топорно
    private Document getPage() throws IOException {
        String url = "https://yandex.ru/pogoda/yekaterinburg/details#10";
        return Jsoup.parse(new URL(url), 3000);
    }

    String weatherToday() {

        try {
            Element tableWth = getPage().select("table[class=weather-table]").first();
            return tableWth.select("div[class=weather-table__wrapper]").text();
        } catch (IOException e) {
            return "Сервис временно недоступен";
        }
    }


}
