package honghu.com.test.push_message;

/**
 * Created by Administrator on 2017/5/5.
 */

//public class PushSmsServlet extends HttpServlet {
//    private static int index = 1;
//
//    public void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        System.out.println(index);
//        // List<Sms> smsList = new ArrayList<Sms>();
//        Sms sms = new Sms(index,
//                "我爱你我爱你我爱你我爱你我爱你我爱你我爱你我爱你我爱你我爱你我爱你我爱你" + index, "2013-03-03",
//                "13522224444");
//        index++;
//        // smsList.add(sms);
//        // sms = new Sms(0, "我真的爱你", "2013-04-04", "13522224444");
//        // smsList.add(sms);
//        // sms = new Sms(1, "我真的真的爱你", "2013-05-05", "13522224444");
//        // smsList.add(sms);
//
//        response.setContentType("text/html");
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("utf-8");
//        PrintWriter out = response.getWriter();
//        Gson gson = new Gson();
//        // 将Sms类的数据转换为json数据格式
//        String json = gson.toJson(sms);
//        out.write(json);
//        out.flush();
//        out.close();
//
//    }
//
//    public void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        this.doGet(request, response);
//    }
//
//}