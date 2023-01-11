import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;


public class HomePageTest {
    private static WebDriver driver;
    private static WebDriverWait wait;


//    Необходимо сделать так, чтобы браузер открывался один раз
    @BeforeClass
    public static void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
//     Открытие браузера во весь экран
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
    }

    @AfterClass
    public static void tearDown() throws IOException {
        var sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File("c:\\tmp\\screenshot.png"));
        driver.quit();
    }
// ----------------------------------------------------------------------------------------------------------------------


//  ТЕСТ НА ПЕРЕХОД ПО ССЫЛКЕ http://intershop5.skillbox.ru/ И ПОПАДАНИЕ НА ГЛАВНУЮ СТРАНИЦУ САЙТА
    @Test
    public void OpenHomePageTest() {
//        Открытие  страницы сайта
        driver.navigate().to("http://intershop5.skillbox.ru/");
//        Элемент раздела "Главная"
        var HomPageElement = By.className("apwidget_title");
//        Проверка переходим ли в раздел "Главная"
        Assert.assertTrue("Не отобразилась главная страница", driver.findElement(HomPageElement).isDisplayed());
    }
//----------------------------------------------------------------------------------------------------------------------


//----------------------------------------------------------------------------------------------------------------------
//  ТЕСТ НА ПЕРЕХОД ИЗ РАЗДЕЛА "Главная" В РАЗДЕЛ "Каталог" И ОБРАТНО НА ГЛАВНУЮ СТРАНИЦУ
    @Test
    public void GoingToCatalogTest() {
//        Открытие  страницы сайта
        driver.navigate().to("http://intershop5.skillbox.ru/");
//        Элемент для перехода в раздел "Главная"
        var HomeElementLocator = By.cssSelector("li#menu-item-26");
//        Элемент для перехода в раздел "Каталог"
        var KatalogElementLocator = By.xpath("//a[text() = 'Каталог']");
//        Элемент заголовка раздела "Каталог"
        var TitleKatalogElementLocator = By.xpath("//h1[text() = 'Каталог']");
//        Элемент блока главной страницы, подтверждающий нахождение пользователя на главной странице
        var PromoWrap1ElementLocator = By.xpath("//*[@class = 'promo-wrap1']");
//        Текст раздела КАТАЛОГ
        var TextCatalog = driver.findElement(KatalogElementLocator).getText();
//        Нажатие в главном меню на раздел "Каталог"
        driver.findElement(KatalogElementLocator).click();
//        Текст заголовка раздела КАТАЛОГ
        var TextTitleCatalog = driver.findElement(TitleKatalogElementLocator).getText();
//        Проверка на нахождение элемента, который подтверждает что мы в разделе "Каталог"
        Assert.assertTrue("Элемент не найден", driver.findElement(TitleKatalogElementLocator).isDisplayed());
//        Проверка на соответсвие текста названия раздела КАТАЛОГ с заголовком раздела КААТЛОГ
        Assert.assertEquals("Неверный текст !", TextCatalog, TextTitleCatalog);
//        Нажатие в главном меню на раздел "Главная"
        driver.findElement(HomeElementLocator).click();
//        Проверка на нахождение элемента, который подтверждает что мы в разделе "Главная"
        Assert.assertTrue("Элемент не найден", driver.findElement(PromoWrap1ElementLocator).isDisplayed());
    }
//----------------------------------------------------------------------------------------------------------------------


//----------------------------------------------------------------------------------------------------------------------
//  ТЕСТ НА ПЕРЕХОД ИЗ "Главная" В РАЗДЕЛ "Мой аккаунт" И НАЗАД НА ГЛАВНУЮ СТРАНИЦУ"
    @Test
    public void GoingToMyAccountTest() {
//        Открытие  страницы сайта
        driver.navigate().to("http://intershop5.skillbox.ru/");
//        Элемент для перехода в раздел "Главная"
        var HomeElementLocator = By.cssSelector("li#menu-item-26");
//        Элемент для перехода в раздел "Мой аккаунт"
        var AccauntElementLocator = By.xpath("(//a[text() = 'Мой аккаунт'])[1]");
//        Элемент заголовка заполнения формы раздела "Мой аккаунт"
        var TitleAccauntElementLocator = By.xpath("//h2[@class='entry-title']");
//        Элемент блока главной страницы, подтверждающий нахождение пользователя на главной странице
        var PromoWrap1ElementLocator = By.xpath("//*[@class = 'promo-wrap1']");
//        Нажатие в главном меню на раздел "Мой аккаунт"
        driver.findElement(AccauntElementLocator).click();
//        Проверка на нахождение элемента, который подтверждает что мы в разделе "Мой аккаунт"
        Assert.assertTrue("Элемент не найден", driver.findElement(TitleAccauntElementLocator).isDisplayed());
//        Нажатие в главном меню на раздел "Главная"
        driver.findElement(HomeElementLocator).click();
//        Проверка на нахождение элемента, который подтверждает что мы в разделе "Главная"
        Assert.assertTrue("Элемент не найден", driver.findElement(PromoWrap1ElementLocator).isDisplayed());
    }
//----------------------------------------------------------------------------------------------------------------------


//----------------------------------------------------------------------------------------------------------------------
//  ТЕСТ НА ПЕРЕХОД ИЗ РАЗДЕЛА "Главная" В РАЗДЕЛ "Корзина" И ВОЗВРАЩЕНИЕ НА ГЛАВНУЮ СТРАНИЦУ САЙТА
    @Test
    public void GoingToMyBasketTest() {
        //        Открытие  страницы сайта
        driver.navigate().to("http://intershop5.skillbox.ru/");
//        Элемент для перехода в раздел "Главная"
        var HomeElementLocator = By.xpath("(//a[@aria-current='page'])[1]");
//        Элемент для перехода в раздел "Корзина"
        var KorzinaElementLocator = By.xpath("(//a[text() = 'Корзина'])[1]");
//        Элемент заголовка раздела "Корзина"
        var TitleKorzinaElementLocator = By.xpath("//h2[@class = 'entry-title']");
//        Элемент подтверждающий нахождения в разделе "Главная"
        var HomeElement = By.className("apwidget_title");
//        Нажатие в главном меню на раздел "Корзина"
        driver.findElement(KorzinaElementLocator).click();
//        Проверка на нахождение элемента, который подтверждает что мы в разделе "Корзина"
        Assert.assertTrue("Элемент не найден", driver.findElement(TitleKorzinaElementLocator).isDisplayed());
//        Нажатие в главном меню на раздел "Главная"
        driver.findElement(HomeElementLocator).click();
//        Проверка на нахождение элемента, который подтверждает что мы в разделе "Главная"
        Assert.assertTrue("Элемент не найден", driver.findElement(HomeElement).isDisplayed());
    }
//----------------------------------------------------------------------------------------------------------------------


//    ----------------------------------------------------------------------------------------------------------------------
//    ПЕРЕХОД В БЛОК КНИГИ ИЗ РАЗДЕЛА "Главная"
    @Test
    public void GoingToBookBlockTest() {
//        Открытие  страницы сайта
        driver.navigate().to("http://intershop5.skillbox.ru/");
//         Элемент для перехода в раздел "Главная"
        var HomeElementLocator = By.xpath("(//div[@class='store-menu']//a)[1]");
//          Элемент блока КНИГИ
        var KnigiLocator = By.xpath("(//*[@class='caption wow fadeIn'])[1]");
//        Элемент заголовка раздела "Книги"
        var TitleKnigiLocator = By.xpath("(//h1)[2]");
//        Элемент подтверждающий нахождения в разделе "Главная"
        var HomeElement = By.className("apwidget_title");
//        Ожидание появления, в блоке КНИГИ, элементов: книги, посмотреть
        wait.until(ExpectedConditions.elementToBeClickable(KnigiLocator));
//        Элемент заголовка блока КНИГИ
        var NameKnigiLocator = By.xpath("(//*[@class='caption wow fadeIn'])[1]//h4");
//        Текст названия блока КНИГИ
        var TextNameKnigiLocator = driver.findElement(NameKnigiLocator).getText();
//        Клик по блоку КНИГИ
        driver.findElement(KnigiLocator).click();
//        Проверка на наличие заголовка раздела КНИГИ
        Assert.assertTrue("Элемент не найден", driver.findElement(TitleKnigiLocator).isDisplayed());
//        Текст заголовка раздела КНИГИ
        var TextTitleKnigiLocator = driver.findElement(TitleKnigiLocator).getText();
//        Проверка на совпадение текста названия блока "Книги" с названем заголовка раздела
        Assert.assertEquals("Неверный текст !", TextNameKnigiLocator, TextTitleKnigiLocator);
//        Переход на главную страницу
        driver.findElement(HomeElementLocator).click();
//        Проверка подтверждающая нахождение на главной странице
        Assert.assertTrue("Элемент не найден", driver.findElement(HomeElement).isDisplayed());
    }
//----------------------------------------------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------------------------------------------
//    ТЕСТ НА ПЕРЕХОД В БЛОК ПЛАНШЕТЫ ИЗ РАЗДЕЛА "Главная"
    @Test
    public void GoingToBlockTabletsTest() {
//        Открытие сайта
        driver.navigate().to("http://intershop5.skillbox.ru/");
//        Элемент заголовка раздела ПЛАНШЕТЫ
        var TitleTabletsElementLocator = By.cssSelector("h1.entry-title.ak-container");
//        Элемент ГЛАВНАЯ в разделе ПЛАНШЕТЫ
        var MainElementlocator = By.xpath("//a[@href = 'http://intershop5.skillbox.ru']");
//        Элемент блока главной страницы, подтверждающий нахождение пользователя на главной странице
        var PromoWrap1ElementLocator = By.xpath("//*[@class = 'promo-wrap1']");
//          Элемент блока ПЛАНШЕТЫ
        var PlanshetLocator = By.xpath("(//*[@class='caption wow fadeIn'])[2]");
//        Элемент заголовка блока ПЛАНШЕТЫ
        var TitlePlanshetLocator = By.xpath("(//*[@class='caption wow fadeIn'])[2]//h4");
//          Ожидание появления, в блоке ПЛАНШЕТЫ, элементов: планшеты, посмотреть
        wait.until(ExpectedConditions.elementToBeClickable(PlanshetLocator));
//          Текст названия блока ПЛАНШЕТЫ на главной странице
        var TextTitlePlanshet = driver.findElement(TitlePlanshetLocator).getText();
//          Клик по элементу блока ПЛАНШЕТЫ
        driver.findElement(PlanshetLocator).click();
//        Проверка на наличие заголовка ПЛАНШЕТЫ в разделе ПЛАНШЕТЫ
        Assert.assertTrue("Элемент не найден", driver.findElement(TitleTabletsElementLocator).isDisplayed());
//        Текст заголовка раздела ПЛАНШЕТЫ
        var TextTitleTablets = driver.findElement(TitleTabletsElementLocator).getText();
//        Проверка текста на совпадение названия блока ПЛАНШЕТЫ с текстом заголовка раздела ПЛАНШЕТЫ
        Assert.assertEquals("Неверный текст", TextTitlePlanshet, TextTitleTablets);
//        Клик по элементу ГЛАВНАЯ в разделе ПЛАНШЕТЫ
        driver.findElement(MainElementlocator).click();
//        Проверка на наличие элемента  блока главной страницы, подтверждающий нахождение пользователя на главной странице
        Assert.assertTrue("Элемент не найден", driver.findElement(PromoWrap1ElementLocator).isDisplayed());
    }


    //----------------------------------------------------------------------------------------------------------------------
//  ПЕРЕХОД ИЗ РАЗДЕЛА "Главная" В РАЗДЕЛ "Оформление заказа", если пользователь не авторизован

    //    Здесь при клике на ОФОРМЛЕНИЕ ЗАКАЗА осуществляется переход в раздел КОРЗИНА, поэтому наблюдаем падение теста
//  Переход в раздел ОФОРМЛЕНИЕ ЗАКАЗА осуществляется только после добавления товара в корзину.
    @Test
    public void GoingToOrderRegistrationTest() {
//        Открытие  страницы сайта
        driver.navigate().to("http://intershop5.skillbox.ru/");
//        Элемент для перехода в раздел "Оформление заказа"
        var OformlenieElementLocator = By.cssSelector("#menu-item-31 > a");
//        Элемент заголовка раздела "Корзина"
        var TitleKorzinaElementLocator = By.xpath("//h2[@class = 'entry-title']");
//        Элемент заголовка раздела ОФОРМЛЕНИЕ ЗАКАЗА
        var TitleOformlenieZakazaLocator = By.xpath("//h2[@class='post-title']");
//        Нажатие в главном меню на раздел "Оформление заказа"
        driver.findElement(OformlenieElementLocator).click();
//        Проверка на нахождение элемента, который подтверждает что мы в разделе "Оформление заказа"
        Assert.assertTrue("Элемент не найден", driver.findElement(TitleOformlenieZakazaLocator).isDisplayed());
    }
//----------------------------------------------------------------------------------------------------------------------


//----------------------------------------------------------------------------------------------------------------------
//    Тест перехода в блок ФОТОАППАРАТЫ
//    Здесь не соответствие заголовков при переходе в блок ФОТОАППАРТЫ
//    ФОТОАППАРАТЫ - ФОТО/ВИДЕО
    @Test
    public void GoingToTheCameraUnitTest() {
//        Открытие сайта
        driver.navigate().to("http://intershop5.skillbox.ru/");
//        Элемент блока ФОТОАППАРАТЫ
        var CameraUnitElementLocator = By.xpath("(//*[@class='caption wow fadeIn'])[3]");
//        Элемент заголовка раздела ФОТОАППАРТЫ
        var TitleCameraUnitElementLocator = By.xpath("//h1[@class='entry-title ak-container']");
//        Элемент ГЛАВНАЯ в разделе ФОТОАППАРАТЫ
        var MainElementlocator = By.xpath("//div[@class='woocommerce-breadcrumb accesspress-breadcrumb']//a[1]");
//        Элемент блока главной страницы, подтверждающий нахождение пользователя на главной странице
        var PromoWrap1ElementLocator = By.xpath("//*[@class = 'promo-wrap1']");
//        Элемент заголовка блока ФОТОАППАРТЫ
        var TitleBlockCameraUnitLocator = By.xpath("(//*[@class='caption wow fadeIn'])[3]//h4");
//          Ожидание появления, в блоке ФОТОАППАРАТЫ, элементов: фотоаппараты, посмотреть
        wait.until(ExpectedConditions.elementToBeClickable(CameraUnitElementLocator));
//          Текст названия блока ФОТОАППАРАТЫ на главной странице
        var TextTitleBlockCameraUnit = driver.findElement(TitleBlockCameraUnitLocator).getText();
//          Клик по элементу блока ФОТОАППАРАТЫ
        driver.findElement(CameraUnitElementLocator).click();
//        Проверка на наличие заголовка ФОТО/ВИДЕО в разделе ФОТОАППАРАТЫ
        Assert.assertTrue("Элемент не найден", driver.findElement(TitleCameraUnitElementLocator).isDisplayed());
    }
//----------------------------------------------------------------------------------------------------------------------


// Тест на переход для просмотра товара IPAD AIR 2020 через баннер УЖЕ В ПРОДАЖЕ
//    Здесь текст товара на банере УЖЕ В ПРОДАЖЕ не совпадает с текстом
//        названия товара, которое указано в разделе (ВСЕ ТОВАРЫ) после клика по баннеру УЖЕ В ПРОДАЖЕ.
//        Тест не проходит конечную проверку.

    @Test
    public void GoingToAlreadyOnSaleTest()
    {
//        Открытие сайта
        driver.navigate().to("http://intershop5.skillbox.ru/");
//        Элемент банера УЖЕ В ПРОДАЖЕ
        var CameraUnitElementLocator = By.xpath("(//div[@class='promo-image'])[4]");
//        Элементы на баннере УЖЕ В ПРОДАЖЕ
        var BanerElementLocator = By.xpath("(//*[@class='caption wow fadeIn'])[4]");
//        Название товара УЖЕ В ПРОДАЖЕ
        var AlreadyOnSaleElementLocator = By.xpath("(//*[@class='caption wow fadeIn'])[4]//div[@class='promo-desc-title']");
//        Название товара, которое указано в разделе (ВСЕ ТОВАРЫ) после клика по баннеру УЖЕ В ПРОДАЖЕ
        var TitleProductElementLocator = By.xpath("//h1[@class='product_title entry-title']");
//        Элемент ГЛАВНАЯ в раздела ВСЕ ТОВАРЫ
        var MainElementlocator = By.xpath("//div[@class='woocommerce-breadcrumb accesspress-breadcrumb']//a[1]");
//        Элемент блока главной страницы, подтверждающий нахождение пользователя на главной странице
        var PromoWrap1ElementLocator = By.xpath("//*[@class = 'promo-wrap1']");
//         Скроллинг до баннера УЖЕ В ПРОДАЖЕ
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(CameraUnitElementLocator));
        actions.perform();
//        Ожидание элементов на банере УЖЕ В ПРОДАЖЕ
        wait.until(ExpectedConditions.presenceOfElementLocated(BanerElementLocator));
//        Текст названия товара на банере УЖЕ В ПРОДАЖЕ
        var TextProductElementLocator = driver.findElement(AlreadyOnSaleElementLocator).getText();
//        Клик по банеру УЖЕ В ПРОДАЖЕ
        driver.findElement(CameraUnitElementLocator).click();
//        Проверка на наличие элемента с названием товара
        Assert.assertTrue("Элемент не найден", driver.findElement(TitleProductElementLocator).isDisplayed());
//        Текст названия товара, которое указано в разделе (ВСЕ ТОВАРЫ) после клика по баннеру УЖЕ В ПРОДАЖЕ
        var TextTitleProduct = driver.findElement(TitleProductElementLocator).getText();
//        Клик по элементу ГЛАВНАЯ в разделе ПЛАНШЕТЫ
        driver.findElement(MainElementlocator).click();
//        Проверка на наличие элемента блока главной страницы, подтверждающий нахождение пользователя на главной странице
        Assert.assertTrue("Элемент не найден", driver.findElement(PromoWrap1ElementLocator).isDisplayed());
//        Проверка совпадения текста товара на банере УЖЕ В ПРОДАЖЕ с текстом
//        названия товара, которое указано в разделе (ВСЕ ТОВАРЫ) после клика по баннеру УЖЕ В ПРОДАЖЕ
        Assert.assertEquals("Неверный текст", TextProductElementLocator, TextTitleProduct);
    }
//    --------------------------------------------------------------------------------------------------------------------
//    Тест на работоспособность кнопки со стрелочкой для возврата наверх страницы

    @Test
    public void AkTopTest()
    {
//        Открытие сайта
        driver.navigate().to("http://intershop5.skillbox.ru/");
//        Элемент кнопка скроллинга наверх страницы
        var AkTopElementLocator = By.cssSelector("div[style='right: 20px;']");
//        Элемент в самом низу страницы сайта
        var SkillboxElementLocator = By.xpath("//*[@class = 'site-info']");
//        Элемент вверха страницы сайта
        var SiteBrandingElementLocator = By.id("site-branding");
//         Скроллинг до конца старницы сайта
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(SkillboxElementLocator));
        actions.perform();
//        Клик по кнопке скроллинга наверх страницы
        driver.findElement(AkTopElementLocator).click();
//        Ожидание исчезновения элемента кнопки для возвращения наверх страницы
        wait.until(ExpectedConditions.invisibilityOfElementLocated(AkTopElementLocator));
//        Проверка на исчезновение элемента
        Assert.assertTrue("Элемент не исчез", driver.findElements(AkTopElementLocator).size()==0);
    }
//    --------------------------------------------------------------------------------------------------------------------


//    Тест на появление элемента с контактами внизу сайта
    @Test
    public void BannerTextTest()
    {
//        Открытие сайта
        driver.navigate().to("http://intershop5.skillbox.ru/");
//        Элемент с контактами внизу сайта
        var BannerTextElementLocator = By.xpath("//*[@class = 'banner-text wow fadeInLeft']");
//        Элемент в самом низу страницы сайта
        var SkillboxElementLocator = By.xpath("//*[@class = 'site-info']");
//         Скроллинг до конца старницы сайта
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(SkillboxElementLocator));
        actions.perform();
//        Ожидание появления элемента с контактами внизу сайта
        wait.until(ExpectedConditions.presenceOfElementLocated(BannerTextElementLocator));
//        Проверка на появление элемента с контактами внизу сайта
        Assert.assertTrue("Элемент не найден", driver.findElement(BannerTextElementLocator).isDisplayed());
    }
//  --------------------------------------------------------------------------------------------------------------------


//    Тест на появление раздела ПРОСМОТРЕННЫЕ ТОВАРЫ
    @Test
    public void ProductsViewedTest() throws InterruptedException {
//        Открытие сайта
        driver.navigate().to("http://intershop5.skillbox.ru/");
//        Элемент товара БАБОЧКА в разделе распродажа
        var ButterflyElementLocator = By.xpath("(//*[@data-wow-delay='1s'])[5]");
//        Элемент в самом низу страницы сайта
        var SkillboxElementLocator = By.xpath("//*[@class = 'site-info']");
//        Элемент ГЛАВНАЯ в раздела ВСЕ ТОВАРЫ
        var MainElementlocator = By.xpath("//div[@class='woocommerce-breadcrumb accesspress-breadcrumb']//a[1]");
//        Элемент ПРОСМОТРЕННЫЕ ТОВАРЫ
        var ProductsViewedElementLocator = By.id("woocommerce_recently_viewed_products-2");
//        Элемент банера УЖЕ В ПРОДАЖЕ
        var BannerElementLocator = By.xpath("(//div[@class='promo-image'])[4]");
//        Скроллинг до элемента товара БАБОЧКА в разделе распродажа
        Actions Banneractions = new Actions(driver);
        Banneractions.moveToElement(driver.findElement(BannerElementLocator));
        Banneractions.perform();
//        Ожидание появления элемента товара БАБОЧКА
        wait.until(ExpectedConditions.elementToBeClickable(ButterflyElementLocator));
//        Клик по элементу товара БАБОЧКА в разделе распродажа
        driver.findElement(ButterflyElementLocator).click();
//        Клик по элементу ГЛАВНАЯ в раздела ВСЕ ТОВАРЫ, в который перешли после клика по товару БАБОЧКА
//        на главной странице
        driver.findElement(MainElementlocator).click();
//        Скроллинг до низа сайта
        Actions Skillboxactions = new Actions(driver);
        Skillboxactions.moveToElement(driver.findElement(SkillboxElementLocator));
        Skillboxactions.perform();
//        Проверка на наличие раздела ПРОСМОТРЕННЫЕ ТОВАРЫ
        Assert.assertTrue("Элемент не найден", driver.findElement(ProductsViewedElementLocator).isDisplayed());
    }

//    Тест на измпенение товаров в слайдере РАСПРОДАЖА
    @Test
    public void SliderSaleTest()
    {
//        Открытие сайта
        driver.navigate().to("http://intershop5.skillbox.ru/");
//        Элемент товара в слайдере РАСПРОДАЖА
        var ProductSaleElementLocator = By.xpath("(//*[@data-wow-delay='2s'])[2]");
//        Элемент заголовка слайдера РАСПРОДАЖА
        var SaleElementLocator = By.xpath("(//*[@class='prod-title'])[1]");
//        Элемент стрелочка вправо в слайдере РАСПРОДАЖА
        var SlickNextElementLocator = By.xpath("(//a[@aria-label='next'])[1]");
//        Элемент, который появляется при первом клике на стрелочку вправо, в слайдере РАСПРОДАЖА
        var NextTovarSliderElementLocator = By.xpath("(//*[@data-wow-delay='2s'])[2]");
//        Скроллинг до  элемента товара в слайдере РАСПРОДАЖА
        Actions  Saleactions = new Actions(driver);
        Saleactions.moveToElement(driver.findElement(ProductSaleElementLocator));
        Saleactions.perform();
//        Ожидание элемента товара в слайдере РАСПРОДАЖА
        wait.until(ExpectedConditions.elementToBeClickable(ProductSaleElementLocator));
//        Наведение курсора мыши на товар в слайдере РАСПРОДАЖА
        new Actions(driver)
                .moveToElement(driver.findElement(ProductSaleElementLocator))
                .perform();
//        Клик по элементу  стрелочка вправо в слайдере РАСПРОДАЖА
        driver.findElement(SlickNextElementLocator).click();
//        Проверка на наличие элемента товара, который появялется при первом клике
//        на стрелочку вправо, в слайдере РАСПРОДАЖА
        Assert.assertTrue("Элемент не найден", driver.findElement(NextTovarSliderElementLocator).isDisplayed());
    }

}
