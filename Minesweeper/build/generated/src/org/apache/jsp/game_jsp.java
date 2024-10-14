package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class game_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>MineSweeper Game</title>\n");
      out.write("        <script>\n");
      out.write("            function creaPagina(){  //solo perché il prof vuole che la tabella venga crosturuita lato server\n");
      out.write("                //richiama la servlet che ritorna l'intera pagina scritta sotto forma di txt\n");
      out.write("                var oob_request = new XMLHttpRequest();\n");
      out.write("                oob_request.onreadystatechange = function(){\n");
      out.write("                    if(this.readyState == 4 && this.status == 200){\n");
      out.write("                        res = oob_request.responseText;\n");
      out.write("                        //qui fai quello che vuoi con res prende il txt in JSON mi sa\n");
      out.write("                    }\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body onload=\"creaPagina()\">\n");
      out.write("        <h1>Hello ");
      out.print(session.getAttribute("username"));
      out.write(" </h1>\n");
      out.write("        <span id=\"table\"> XXX </span>\n");
      out.write("        <form action=\"getValue\" method=\"get\">\n");
      out.write("            Column: <select id=\"colSelected\">\n");
      out.write("                <option>1</option>\n");
      out.write("                <option>2</option>\n");
      out.write("                <option>3</option>\n");
      out.write("                <option>4</option>\n");
      out.write("                <option>5</option>\n");
      out.write("                <option>6</option>\n");
      out.write("                <option>7</option>\n");
      out.write("                <option>8</option>\n");
      out.write("                <option>9</option>\n");
      out.write("            </select>\n");
      out.write("            Column: <select id=\"colSelected\">\n");
      out.write("                <option>1</option>\n");
      out.write("                <option>2</option>\n");
      out.write("                <option>3</option>\n");
      out.write("                <option>4</option>\n");
      out.write("                <option>5</option>\n");
      out.write("                <option>6</option>\n");
      out.write("                <option>7</option>\n");
      out.write("                <option>8</option>\n");
      out.write("                <option>9</option>\n");
      out.write("            </select>\n");
      out.write("        </form>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
