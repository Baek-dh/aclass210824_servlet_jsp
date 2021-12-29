/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.73
 * Generated at: 2021-12-24 10:03:15 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"ko\">\r\n");
      out.write("<head>\r\n");
      out.write("	<meta charset=\"UTF-8\">\r\n");
      out.write("	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("	<title>크롤링</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	<h1>스페이스 클라우드 공간 정보 크롤링</h1>\r\n");
      out.write("	주소 입력 : <input type=\"text\" id=\"url\" size=\"100\"><br>\r\n");
      out.write("	\r\n");
      out.write("	전화 번호 : <input type=\"text\" id=\"spacePno\" name=\"spacePno\"><br>\r\n");
      out.write("	\r\n");
      out.write("	<br>\r\n");
      out.write("	공간 유형<br>\r\n");
      out.write("	<table>\r\n");
      out.write("		<tr>\r\n");
      out.write("			<td><input type=\"radio\" name=\"spaceType\" value=\"1\" id=\"t1\"><label for=\"t1\">라이브방송</label></td>\r\n");
      out.write("			<td><input type=\"radio\" name=\"spaceType\" value=\"2\" id=\"t2\"><label for=\"t2\">카페</label></td>\r\n");
      out.write("			<td><input type=\"radio\" name=\"spaceType\" value=\"3\" id=\"t3\"><label for=\"t3\">파티룸</label></td>\r\n");
      out.write("			<td><input type=\"radio\" name=\"spaceType\" value=\"4\" id=\"4\"><label for=\"t4\">악기연습실</label></td>\r\n");
      out.write("		</tr>\r\n");
      out.write("		<tr>\r\n");
      out.write("			<td><input type=\"radio\" name=\"spaceType\" value=\"5\" id=\"t5\"><label for=\"t5\">실외촬영</label></td>\r\n");
      out.write("			<td><input type=\"radio\" name=\"spaceType\" value=\"6\" id=\"t6\"><label for=\"t6\">공연장</label></td>\r\n");
      out.write("			<td><input type=\"radio\" name=\"spaceType\" value=\"7\" id=\"t7\"><label for=\"t7\">운동시설</label></td>\r\n");
      out.write("			<td><input type=\"radio\" name=\"spaceType\" value=\"8\" id=\"t8\"><label for=\"t8\">스몰웨딩</label></td>\r\n");
      out.write("		</tr>\r\n");
      out.write("		<tr>\r\n");
      out.write("			<td><input type=\"radio\" name=\"spaceType\" value=\"9\" id=\"t9\"><label for=\"t9\">회의실</label></td>\r\n");
      out.write("			<td><input type=\"radio\" name=\"spaceType\" value=\"10\" id=\"t10\"><label for=\"t10\">촬영스튜디오</label></td>\r\n");
      out.write("			<td><input type=\"radio\" name=\"spaceType\" value=\"11\" id=\"t11\"><label for=\"t11\">독립오피스</label></td>\r\n");
      out.write("			<td><input type=\"radio\" name=\"spaceType\" value=\"12\" id=\"t12\"><label for=\"t12\">세미나실</label></td>\r\n");
      out.write("		</tr>\r\n");
      out.write("	</table>\r\n");
      out.write("	\r\n");
      out.write("	\r\n");
      out.write("	<br>\r\n");
      out.write("	옵션<br>\r\n");
      out.write("	<table>\r\n");
      out.write("		<tr>\r\n");
      out.write("			<td><input type=\"checkBox\" name=\"option\" value=\"15\" id=\"o15\"><label for=\"o15\">화이트 보드</label></td>\r\n");
      out.write("			<td><input type=\"checkBox\" name=\"option\" value=\"16\" id=\"o16\"><label for=\"o16\">취사시설</label></td>\r\n");
      out.write("			<td><input type=\"checkBox\" name=\"option\" value=\"17\" id=\"o17\"><label for=\"o17\">탈의실</label></td>\r\n");
      out.write("			<td><input type=\"checkBox\" name=\"option\" value=\"18\" id=\"o18\"><label for=\"o18\">음식물 반입가능</label></td>\r\n");
      out.write("		</tr>\r\n");
      out.write("		<tr>	\r\n");
      out.write("			<td><input type=\"checkBox\" name=\"option\" value=\"19\" id=\"o19\"><label for=\"o19\">음향/마이크</label></td>\r\n");
      out.write("			<td><input type=\"checkBox\" name=\"option\" value=\"20\" id=\"o20\"><label for=\"o20\">전신거울</label></td>\r\n");
      out.write("			<td><input type=\"checkBox\" name=\"option\" value=\"21\" id=\"o21\"><label for=\"o21\">반려동물 동반가능</label></td>\r\n");
      out.write("			<td><input type=\"checkBox\" name=\"option\" value=\"22\" id=\"o22\"><label for=\"o22\">복사/인쇄기</label></td>\r\n");
      out.write("		</tr>\r\n");
      out.write("		<tr>\r\n");
      out.write("			<td><input type=\"checkBox\" name=\"option\" value=\"23\" id=\"o23\"><label for=\"o23\">샤워시설기</label></td>\r\n");
      out.write("			<td><input type=\"checkBox\" name=\"option\" value=\"24\" id=\"o24\"><label for=\"o24\">금연</label></td>\r\n");
      out.write("			<td><input type=\"checkBox\" name=\"option\" value=\"25\" id=\"o25\"><label for=\"o25\">인터넷/WIFI</label></td>\r\n");
      out.write("		</tr>\r\n");
      out.write("	</table>\r\n");
      out.write("	\r\n");
      out.write("	<button id=\"start\">크롤링 시작</button>\r\n");
      out.write("	\r\n");
      out.write("	<script src=\"https://code.jquery.com/jquery-3.6.0.min.js\" integrity=\"sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("	<script>\r\n");
      out.write("		$(\"#start\").on(\"click\", function(){\r\n");
      out.write("			\r\n");
      out.write("			const optionArr = [];\r\n");
      out.write("			$(\"[name=option]:checked\").each(function(){\r\n");
      out.write("				optionArr.push($(this).val());\r\n");
      out.write("			})\r\n");
      out.write("			\r\n");
      out.write("			$.ajax({\r\n");
      out.write("				url : \"crawling\",\r\n");
      out.write("				data : {\"url\" : $(\"#url\").val(),\r\n");
      out.write("						\"spaceType\" : $(\"[name=spaceType]:checked\").val(),\r\n");
      out.write("						\"optionArr\" : optionArr,\r\n");
      out.write("						\"spacePno\" : $(\"#spacePno\").val()},\r\n");
      out.write("			  	traditional : true,\r\n");
      out.write("				success : function(){\r\n");
      out.write("					console.log(\"크롤링 완료\");\r\n");
      out.write("				}, error : function(req, status, error){\r\n");
      out.write("		            console.log(\"댓글 목록 조회 실패\");\r\n");
      out.write("		            console.log(req.responseText);\r\n");
      out.write("		        }\r\n");
      out.write("			})\r\n");
      out.write("			\r\n");
      out.write("		});\r\n");
      out.write("	</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
