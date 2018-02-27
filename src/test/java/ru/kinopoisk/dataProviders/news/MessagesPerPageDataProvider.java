package ru.kinopoisk.dataProviders.news;
import org.testng.annotations.DataProvider;
import static ru.kinopoisk.pages.news.NewsPreviewPage.quantityOfMessagesPerPage.*;

public class MessagesPerPageDataProvider {

        @DataProvider(name = "quantityOfMessages")
        public Object[][] quantityOfMessagesPerPage() {
            return new Object[][]{
                    {TEN},
                    {TWENTY_FIVE},
                    {FIFTY},
                    {SEVENTY_FIVE},
                    {HUNDRED},
                    {TWO_HUNDRED}
            };
        }

}
