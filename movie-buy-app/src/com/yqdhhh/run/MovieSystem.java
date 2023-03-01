package com.yqdhhh.run;

import com.yqdhhh.bean.Business;
import com.yqdhhh.bean.Customer;
import com.yqdhhh.bean.Movie;
import com.yqdhhh.bean.User;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MovieSystem {
    /**
       定义系统的数据容器用户存储数据
       1，存储很多用户（客户对象，商家对象）
     */
    public static final List<User> ALL_USERS = new ArrayList<>();

    /**
       2,存储系统全部商家和其排片
           商家1 = [p1,p2,p3,...]
           商家2 = [p2,p3,...]
            ...
     */

    public static final Map<Business, List<Movie>> ALL_MOVIE = new HashMap<>();
    public static final Map<Customer, List<Movie>> BY_MOVIE = new HashMap<>();

    public static final Scanner SYC_SC = new Scanner(System.in);

    public static Map<String, Integer> byMovieNumber = new HashMap<>();
    // 定义一个静态的User类型的变量记住当前登录成功的用户对象
    public static User loginUser;
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final Logger LOGGER = LoggerFactory.getLogger("MovieSystem.class");

    /**
       3,准备一些测试数据
     */
    static {
        Customer c = new Customer();
        c.setLoginName("ldh");
        c.setPassWord("123456");
        c.setUserName("黑马刘德华");
        c.setSex("男");
        c.setMoney(10000);
        c.setPhone("17855636688");
        ALL_USERS.add(c);

        Customer c1 = new Customer();
        c1.setLoginName("gzl");
        c1.setPassWord("123456");
        c1.setUserName("黑马关之琳");
        c1.setSex("女");
        c1.setMoney(2000);
        c1.setPhone("15622337778");
        ALL_USERS.add(c1);

        Business b = new Business();
        b.setLoginName("baozugong");
        b.setPassWord("123456");
        b.setUserName("黑马包租公");
        b.setSex("男");
        b.setMoney(0);
        b.setPhone("13452367866");
        b.setAddress("火星6号2B二层");
        b.setShopName("甜甜圈国际影城");
        ALL_USERS.add(b);
        // 注意，商家一定需要加入到店铺拍片信息中去
        List<Movie> movies = new ArrayList<>();
        ALL_MOVIE.put(b, movies);

        Business b2 = new Business();
        b2.setLoginName("baozupo");
        b2.setPassWord("123456");
        b2.setUserName("黑马包租婆");
        b2.setSex("女");
        b2.setMoney(0);
        b2.setPhone("13452367865");
        b2.setAddress("火星8号8B八层");
        b2.setShopName("巧克力国际影城");
        ALL_USERS.add(b2);
        // 注意，商家一定需要加入到店铺拍片信息中去
        List<Movie> movies1 = new ArrayList<>();
        ALL_MOVIE.put(b2, movies1);
    }

    public static void main(String[] args) {
        showMain();
    }

    /**
       首页展示
     */
    private static void showMain() {
        while (true) {
        System.out.println("===========黑马电影首页============");
        System.out.println("1, 登录");
        System.out.println("2, 用户注册");
        System.out.println("2, 商家入驻");
            System.out.print("请输入操作命令：");
            String command = SYC_SC.nextLine();
            switch (command){
                case "1": // 登录
                    login();
                    break;
                case "2": // 用户注册
                    customerEnroll();
                    break;
                case "3": // 商家入驻
                    businessEnroll();
                    break;
                default:
                    System.out.println("无此选项！");
            }
        }
    }

    /**
      商家入驻
     */
    private static void businessEnroll() {
        System.out.println("===========商家入驻页面============");
        Business business = new Business();
        while (true) {
            System.out.println("请输入账号：");
            String byLoginName = SYC_SC.nextLine();
            if (byLoginName.matches("[a-zA-Z]{3,9}")) {
                business.setLoginName(byLoginName);
                while (true) {
                    System.out.println("请输入密码：");
                    String byPassword = SYC_SC.nextLine();
                    if (byPassword.matches("^(?=.*[a-z])(?=.*\\d)[a-zA-Z\\d]{8,}$")){
                        business.setPassWord(byPassword);
                        while (true) {
                            System.out.println("请输入用户名：");
                            String byUserName = SYC_SC.nextLine();
                            if (byUserName.matches("[\\u4e00-\\u9fa5]{1,9}")){
                                business.setUserName(byUserName);
                                while (true) {
                                    System.out.println("请输入您的性别：");
                                    String bySex = SYC_SC.nextLine();
                                    if (bySex.matches("[男|女]")){
                                        business.setSex(bySex);
                                        while (true) {
                                            System.out.println("请输入您的电话号码：");
                                            String byPhone = SYC_SC.nextLine();
                                            if (byPhone.matches("1[3-9]\\d{9}")){
                                                business.setPhone(byPhone);
                                                while (true) {
                                                    System.out.println("请输入您的账户金额：");
                                                    String byMoney = SYC_SC.nextLine();
                                                    if (byMoney.matches("(^(([0-9]|([1-9][0-9]{0,9}))((\\.[0-9]{1,2})?))$)")){
                                                        business.setMoney(Double.parseDouble(byMoney));
                                                        while (true) {
                                                            System.out.println("请输入店铺名称：");
                                                            String byShopName = SYC_SC.nextLine();
                                                            if (byShopName.matches("[\\u4e00-\\u9fa5]{1,9}")){
                                                                business.setShopName(byShopName);
                                                                while (true) {
                                                                    System.out.println("请输入店铺地址：");
                                                                    String byAddress = SYC_SC.nextLine();
                                                                    if (byAddress.matches("^.+(路|街).+.+号楼.+层.+(A|B).*$")){
                                                                        business.setAddress(byAddress);
                                                                        System.out.println("恭喜您，入驻成功！");
                                                                        return;
                                                                    }else {
                                                                        System.out.println("地址格式有误！");
                                                                    }
                                                                }
                                                            }else {
                                                                System.out.println("店铺格式有误！");
                                                            }
                                                        }
                                                    }else {
                                                        System.out.println("金额有误！");
                                                    }
                                                }
                                            }else {
                                                System.out.println("电话号码有误！");
                                            }
                                        }
                                    }else {
                                        System.out.println("性别有误！");
                                    }
                                }
                            }else {
                                System.out.println("用户名有误");
                            }
                        }
                    }else {
                        System.out.println("密码格式错误！");
                    }
                }
            }else {
                System.out.println("账号格式错误！");
            }
        }
    }

    /**
      用户注册
     */
    private static void customerEnroll() {
        System.out.println("===========用户注册页面============");
        Customer customer = new Customer();
        while (true) {
            System.out.println("请输入账号：");
            String byLoginName = SYC_SC.nextLine();
            if (byLoginName.matches("[a-zA-Z]{3,9}")) {
                customer.setLoginName(byLoginName);
                while (true) {
                    System.out.println("请输入密码：");
                    String byPassword = SYC_SC.nextLine();
                    if (byPassword.matches("^(?=.*[a-z])(?=.*\\d)[a-zA-Z\\d]{8,}$")){
                        customer.setPassWord(byPassword);
                        while (true) {
                            System.out.println("请输入用户名：");
                            String byUserName = SYC_SC.nextLine();
                            if (byUserName.matches("[\\u4e00-\\u9fa5]{1,9}")){
                                customer.setUserName(byUserName);
                                while (true) {
                                    System.out.println("请输入您的性别：");
                                    String bySex = SYC_SC.nextLine();
                                    if (bySex.matches("[男|女]")){
                                        customer.setSex(bySex);
                                        while (true) {
                                            System.out.println("请输入您的电话号码：");
                                            String byPhone = SYC_SC.nextLine();
                                            if (byPhone.matches("1[3-9]\\d{9}")){
                                                customer.setPhone(byPhone);
                                                while (true) {
                                                    System.out.println("请输入您的账户金额：");
                                                    String byMoney = SYC_SC.nextLine();
                                                    if (byMoney.matches("(^(([0-9]|([1-9][0-9]{0,9}))((\\.[0-9]{1,2})?))$)")){
                                                        customer.setMoney(Double.parseDouble(byMoney));
                                                        System.out.println("恭喜您，注册成功！");
                                                        return;
                                                    }else {
                                                        System.out.println("金额有误！");
                                                    }
                                                }
                                            }else {
                                                System.out.println("电话号码有误！");
                                            }
                                        }
                                    }else {
                                        System.out.println("性别有误！");
                                    }
                                }
                            }else {
                                System.out.println("用户名有误");
                            }
                        }
                    }else {
                        System.out.println("密码格式错误！");
                    }
                }
            }else {
                System.out.println("账号格式错误！");
            }
        }
    }

    /**
       登录
     */
    public static void login(){
        while (true) {
            // 1,根据登录名称查询用户对象
            System.out.println("请您输入登录名称");
            String loginName = SYC_SC.nextLine();
            User u = getUserByLoginName(loginName);
            // 2,判断用户对象是否存在，存在说明登录名称正确
            if (u != null){
                // 3,比对密码是否正确
                System.out.println("请您输入登录密码");
                String passWord = SYC_SC.nextLine();
                if (u.getPassWord().equals(passWord)){
                    // 登录成功
                    // 判断是用户登录的，还是商家登陆的
                    loginUser = u; // 记住登陆成功的用户
                    LOGGER.info(u.getUserName() + "登录了系统");
                    if (u instanceof Customer){
                        // 当前登录的是普通用户
                        showCustomerMain();
                    }else {
                        // 当前登录的是商家用户
                        showBusinessMain();
                    }
                    return;
                }else {
                    System.out.println("密码错误！");
                }
            }else {
                System.out.println("登录名称错误！");
            }
        }
    }

    /**
     判断登录名称是否在集合里面
     */
    public static User getUserByLoginName(String loginName){
        for (User User : ALL_USERS) {
            // 判断这个用户的登录名称是否事我们想要的
            if (User.getLoginName().equals(loginName)){
                return User;
            }
        }
        return null; // 查无此用户登录名称
    }

    /**
      商家后台操作页面
     */
    private static void showBusinessMain() {
        while (true) {
            System.out.println("===========黑马电影商家界面============");
            LOGGER.info(loginUser.getUserName() + "进入了商家界面");
            System.out.println(loginUser.getUserName() + (loginUser.getSex() == "男"?"先生" : "女士")
            + "欢迎您进入系统");
            System.out.println("1,展示详情");
            System.out.println("2,上架电影");
            System.out.println("3,下架电影");
            System.out.println("4,修改电影");
            System.out.println("5,退出");

            System.out.print("请您输入操作命令：");
            String command = SYC_SC.nextLine();
            switch (command){
                case "1":
                    // 展示全部排片信息
                    showBusinessInfos();
                    break;
                case "2":
                    // 上架电影信息
                    addMovies();
                    break;
                case "3":
                    // 下架电影信息
                    deleteMovie();
                    break;
                case "4":
                    // 修改电影信息
                    updateMovie();
                    break;
                case "5":
                    System.out.println(loginUser.getUserName() + "您好，您成功退出商家界面");
                    return;
                default:
                    System.out.println("错误！！");
                    break;
            }
        }
    }

    /**
      展示商家的详细：展示当前商家的信息
     */
    private static void showBusinessInfos() {
        // 根据商家对象(就是登录的用户loginUser)，作为Map集合的键 提取对应的值就是其排片信息：Map<Business, List<Movies>> ALL_MOVIES

        System.out.println("===========黑马电影商家详情============");
        Business business = (Business) loginUser;
        System.out.println("个人信息如下：");
        System.out.println(business.getShopName() + "\t电话:" + business.getPhone() + "\t地址:"
        + business.getAddress() + "总营收:" + business.getMoney());

        System.out.println("影院排片信息如下:");
        List<Movie> movies = ALL_MOVIE.get(business);
        if (movies.size() > 0){
            System.out.println("影片名称\t\t主演\t\t时长\t\t评分\t\t票价" +
                    "\t\t余票\t\t放映时间");
            for (Movie movie : movies) {
                System.out.println(movie.getName() + "\t\t"
                + movie.getActor() + "\t\t" + movie.getTime() + "\t\t"
                + movie.getScore() + "\t\t" + movie.getPrice() + "\t\t"
                + movie.getNumber() + "\t\t" + sdf.format(movie.getStartTime()));
            }
        }else {
            System.out.println("当前无排片信息");
        }
    }

    /**
      商家进行电影上架
     Map<Business, List<Movies>> ALL_MOVIES
     u1 = [p1,p2,p3]
     u2 = [p1,p2,p3]
     */
    private static void addMovies() {
        System.out.println("===========黑马电影上架影片============");
        Business business = (Business) loginUser;
        List<Movie> movies = ALL_MOVIE.get(business);
        System.out.print("请您输入片名:");
        String name = SYC_SC.nextLine();
        System.out.print("请您输入主演:");
        String actor = SYC_SC.nextLine();
        System.out.print("请您输入时长:");
        String time = SYC_SC.nextLine();
        System.out.print("请您输入票价:");
        String price = SYC_SC.nextLine();
        System.out.print("请您输入票数:");
        String totalNumber = SYC_SC.nextLine();
        while (true) {
            try {
            System.out.print("请您输入影片放映时间:");
            String stime = SYC_SC.nextLine();

            // 封装电影对象
            // public Movie(String name, String actor, double time, double price, int number, Date startTime)
                Movie movie = new Movie(name, actor, Double.parseDouble(time), Double.parseDouble(price),
                        Integer.parseInt(totalNumber), sdf.parse(stime));
                movies.add(movie);
                System.out.println("您已经成功上架：《" + movie.getName() + "》");
                return;
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("时间解析出现问题");
            }
        }
    }

    /**
      下架电影
     */
    private static void deleteMovie() {
        System.out.println("===========黑马电影下架影片============");
        Business business = (Business) loginUser;
        List<Movie> movies = ALL_MOVIE.get(business);
        if (movies.size() == 0){
            System.out.println("当前无片可以下架！");
            return;
        }
        // 2,让用户选择需要下架的电影名称
        while (true) {
            System.out.print("请您输入需要下架的电影名称：");
            String movieName = SYC_SC.nextLine();
            Movie movie = getMovieByName(movieName);
            if (movie != null){
                movies.remove(movie);
                System.out.println("您的店铺当前已经成功下架：" + movie.getName());
                showBusinessInfos();
                return;
            }else {
                System.out.println("您的店铺没有上架该电影！");
                System.out.println("请问继续下架吗？y/n");
                String command = SYC_SC.nextLine();
                switch (command){
                    case "y":
                        break;
                    default:
                        System.out.println("好的");
                        return;
                }
            }
        }

        // 3,去查询有没有这个影片对象
    }

    /**
      去查当前商家下的排片
     */
    public static Movie getMovieByName(String movieName){
        Business business = (Business) loginUser;
        List<Movie> movies = ALL_MOVIE.get(business);
        for (Movie movie : movies) {
            if (movie.getName().contains(movieName)){
                return movie;
            }
        }
        return null;
    }

    /**
      修改电影
     */
    private static void updateMovie() {
        System.out.println("===========黑马电影修改影片============");
        Business business = (Business) loginUser;
        List<Movie> movies = ALL_MOVIE.get(business);
        if (movies.size() == 0){
            System.out.println("当前无片可以修改！");
            return;
        }

        while (true) {
            System.out.print("请您输入需要修改的电影名称：");
            String movieName = SYC_SC.nextLine();
            Movie movie = getMovieByName(movieName);
            if (movie != null){
                // 修改它
                System.out.print("请您输入修改后片名:");
                String name = SYC_SC.nextLine();
                System.out.print("请您输入修改后主演:");
                String actor = SYC_SC.nextLine();
                System.out.print("请您输入修改后时长:");
                String time = SYC_SC.nextLine();
                System.out.print("请您输入修改后票价:");
                String price = SYC_SC.nextLine();
                System.out.print("请您输入修改后票数:");
                String totalNumber = SYC_SC.nextLine();

                while (true) {
                    try {
                        System.out.print("请您输入修改后影片放映时间:");
                        String stime = SYC_SC.nextLine();

                        movie.setName(name);
                        movie.setActor(actor);
                        movie.setTime(Double.parseDouble(time));
                        movie.setPrice(Double.parseDouble(price));
                        movie.setNumber(Integer.parseInt(totalNumber));
                        movie.setStartTime(sdf.parse(stime));

                        System.out.println("修改成功！");
                        showBusinessInfos();
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("时间解析出现问题");
                    }
                }
            }else {
                System.out.println("您的店铺没有上架该电影！");
                System.out.println("请问继续修改吗？y/n");
                String command = SYC_SC.nextLine();
                switch (command) {
                    case "y":
                        break;
                    default:
                        System.out.println("好的");
                        return;
                }
            }
        }
    }

    /**
      用户操作页面
     */
    private static void showCustomerMain() {
        while (true) {
            System.out.println("===========黑马电影客户界面============");
            System.out.println(loginUser.getUserName() + (loginUser.getSex() == "男"?"先生" : "女士")
                    + "欢迎您进入系统\t余额：" + loginUser.getMoney());
            System.out.println("1,展示影片");
            System.out.println("2,查询电影");
            System.out.println("3,评分功能");
            System.out.println("4,购票功能");
            System.out.println("5,退出系统");
            System.out.print("请您输入操作命令：");
            String command = SYC_SC.nextLine();
            switch (command){
                case "1":
                    // 展示全部排片信息
                    showAllMovies();
                    break;
                case "2":
                    // 查询全部电影
                    queryByName();
                    break;
                case "3":
                    // 评分功能
                    scoreMovies();
                    break;
                case "4":
                    // 购票功能
                    buyMovie();
                    break;
                case "5":
                    System.out.println("您好，您成功退出用户界面");
                    return;
                default:
                    System.out.println("错误！！");
                    break;
            }
        }
    }

    /**
      评分系统
     */
    private static void scoreMovies() {
        System.out.println("===========电影评分界面============");
        Customer customer = (Customer) loginUser;
        if (customer.getByNumber().size() >= 1){
            System.out.println("您当前可评分的电影如下：");
            customer.getByNumber().forEach((k, v) ->{
                System.out.println(k + "\t->\t" + v);
            });
            System.out.println("请输入你需要评分电影名称：");
            String command = SYC_SC.nextLine();
            Collection<List<Movie>> movies = ALL_MOVIE.values();
            for (List<Movie> movie : movies) {
                for (Movie movie1 : movie) {
                    if (movie1.getName().contains(command)){
                        System.out.print("请输入您对影片的评分：");
                        Integer commands = null;
                        commands = SYC_SC.nextInt((10)+1);
                        if (commands <= 10 && commands >= 0) {
                            movie1.setScore(commands);
                            System.out.println("恭喜，您评分成功！");
                            System.out.println(movie1.getName() + "\t\t"
                                    + movie1.getActor() + "\t\t" + movie1.getTime() + "\t\t"
                                    + movie1.getScore() + "\t\t" + movie1.getPrice() + "\t\t"
                                    + movie1.getNumber() + "\t\t" + sdf.format(movie1.getStartTime()));
                            return;
                        }else {
                            System.out.println("错误评分！");
                            break;
                        }
                    }else {
                        System.out.println("无此影片，您是否继续搜索 y/n");
                        String commands1 = SYC_SC.nextLine();
                        switch (commands1) {
                            case "y":
                                break;
                            default:
                                System.out.println("好的");
                        }
                    }
                }
            }
        }else{
            System.out.println("抱歉，您还未购票！");
        }
    }

    /**
      查询系统
     */
    private static void queryByName() {
        System.out.println("===========电影查询界面============");
//        while (true) {
//            System.out.print("请输入电影名称：");
//            String command = SYC_SC.nextLine();
//            Collection<List<Movie>> movies = ALL_MOVIE.values();
//            for (List<Movie> movie : movies) {
//                for (Movie movie1 : movie) {
//                    if (movie1.getName().contains(command)) {
//                        System.out.println(movie1.getName() + "\t\t"
//                                + movie1.getActor() + "\t\t" + movie1.getTime() + "\t\t"
//                                + movie1.getScore() + "\t\t" + movie1.getPrice() + "\t\t"
//                                + movie1.getNumber() + "\t\t" + sdf.format(movie1.getStartTime()));
//                        return;
//                    }else{
//                        System.out.println("无此影片，您是否继续搜索 y/n");
//                        String commands = SYC_SC.nextLine();
//                        switch (command) {
//                            case "y":
//                                break;
//                            default:
//                                System.out.println("好的");
//                        }
//                    }
//                }
//            }
//        }
        while (true) {
            System.out.print("请输入店铺名称：");
            String shopName = SYC_SC.nextLine();
            // 查询是否存在该商家
            Business business = getBusinessByShopName(shopName);
            if (business != null){
                // 2,此商家全部的排片
                List<Movie> movies = ALL_MOVIE.get(business);
                if (movies.size() > 0){
                    while (true) {
                        System.out.print("请您输入需要查询的电影名称：");
                        String movieName = SYC_SC.nextLine();
                        Movie movie = getMovieByShopAndName(business, movieName);
                        if (movie != null) {
                            System.out.println(movie.getName() + "\t\t"
                                    + movie.getActor() + "\t\t" + movie.getTime() + "\t\t"
                                    + movie.getScore() + "\t\t" + movie.getPrice() + "\t\t"
                                    + movie.getNumber() + "\t\t" + sdf.format(movie.getStartTime()));
                            return;
                        }else {
                            System.out.println("无此电影！");
                        }
                    }
                }else {
                    System.out.println("该影院关门中");
                    System.out.println("是否重新继续查询？ y/n");
                    String command = SYC_SC.nextLine();
                    switch (command) {
                        case "y":
                            break;
                        default:
                            System.out.println("好的");
                            return;
                    }
                }
            }else{
                System.out.println("对不起，没有该店铺！请确认");
            }
        }
    }

    /**
      用户购票功能 ALL_MOVIES = {b1=[p1,p2,p3], b2=[p1,p2,p3]}
     */
    private static void buyMovie() {
        showAllMovies();
        System.out.println("===========用户购票界面============");
        while (true) {
            System.out.print("请输入店铺名称：");
            String shopName = SYC_SC.nextLine();
            // 查询是否存在该商家
            Business business = getBusinessByShopName(shopName);
            if (business == null){
                System.out.println("对不起，没有该店铺！请确认");
            }else{
                // 2,此商家全部的排片
                List<Movie> movies = ALL_MOVIE.get(business);
                if (movies.size() > 0){
                    // 4,开始进行选片购买
                    while (true) {
                        System.out.print("请您输入需要购买的电影名称：");
                        String movieName = SYC_SC.nextLine();
                        Movie movie = getMovieByShopAndName(business, movieName);
                        if (movie != null){
                            while (true) {
                                System.out.print("请输入购买的票数：");
                                String number = SYC_SC.nextLine();
                                int buyNumber = Integer.parseInt(number);
                                if (movie.getNumber() >= buyNumber) {
                                    BigDecimal money = BigDecimal.valueOf(movie.getPrice()).
                                            multiply(BigDecimal.valueOf(buyNumber));
                                    Double movieMoney = Double.valueOf(money.toString());
                                    if (loginUser.getMoney() >= movieMoney){
                                        business.setMoney(business.getMoney() + movieMoney);
                                        loginUser.setMoney(loginUser.getMoney() - movieMoney);
                                        movie.setNumber(movie.getNumber() - buyNumber);

                                        byMovieNumber.put(movie.getName(), buyNumber);
                                        Customer customer = (Customer) loginUser;
                                        customer.setByNumber(byMovieNumber);

                                        System.out.println("购票成功！您购买了" + movie.getName() + buyNumber
                                                + "张，您当前余额为：" + loginUser.getMoney() + "总金额为：" + movieMoney);
                                        return;
                                    }else{
                                        System.out.println("对不起，您的余额不足，您当前余额为：" + loginUser.getMoney());
                                        System.out.println("是否重新继续买票？ y/n");
                                        String command = SYC_SC.nextLine();
                                        switch (command) {
                                            case "y":
                                                break;
                                            default:
                                                System.out.println("好的");
                                        }
                                    }
                                }else {
                                    System.out.println("票数不足！您当前最多购买" + movie.getNumber() +"张");
                                    System.out.println("是否重新继续买票？ y/n");
                                    String command = SYC_SC.nextLine();
                                    switch (command) {
                                        case "y":
                                            break;
                                        default:
                                            System.out.println("好的");
                                    }
                                }
                            }
                        }else{
                            System.out.println("无此电影！");
                        }
                    }
                    // 去当前商家下，查询该电影对象
                }else {
                    System.out.println("该影院关门中");
                    System.out.println("是否重新继续买票？ y/n");
                    String command = SYC_SC.nextLine();
                    switch (command) {
                        case "y":
                            break;
                        default:
                            System.out.println("好的");
                            return;
                    }
                }
            }
        }
    }

    /**
     通过商家对象获取电影对象
     */
    public static Movie getMovieByShopAndName(Business business, String name){
        List<Movie> movies = ALL_MOVIE.get(business);
        for (Movie movie : movies) {
            if (movie.getName().contains(name)){
                return movie;
            }
        }return null;
    }

    /**
      根据商家店铺名称查询商家对象
     * @return
     */
    public static Business getBusinessByShopName(String shopName){
        Set<Business> businesses = ALL_MOVIE.keySet();
        for (Business business : businesses) {
            if (business.getShopName().equals(shopName)){
                return business;
            }
        }return null;
    }

    /**
      展示全部商家和其拍片信息
     */
    private static void showAllMovies() {
        System.out.println("===========全部商家排片信息============");
        ALL_MOVIE.forEach((business, movies) -> {
            System.out.println(business.getShopName() + "\t电话:" + business.getPhone() + "\t地址:"
                    + business.getAddress() + "总营收:" + business.getMoney());
            System.out.println("影片名称\t\t主演\t\t时长\t\t评分\t\t票价" +
                    "\t\t余票\t\t放映时间");
            for (Movie movie : movies) {
                System.out.println(movie.getName() + "\t\t"
                        + movie.getActor() + "\t\t" + movie.getTime() + "\t\t"
                        + movie.getScore() + "\t\t" + movie.getPrice() + "\t\t"
                        + movie.getNumber() + "\t\t" + sdf.format(movie.getStartTime()));
            }
        });
    }
}
