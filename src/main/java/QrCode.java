import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.krysalis.barcode4j.impl.datamatrix.DataMatrixBean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

/**
 * Servlet implementation class QrCode
 */
@WebServlet("/qrcode")
public class QrCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QrCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/index.html").forward(request, response);
		
		// TODO Auto-generated method stub
		
//		//response.getWriter().append("Served at: ").append(request.getContextPath());
//		 response.setContentType("text/html");
//	        response.getWriter().println("Bienvenue sur le générateur de codes-barres.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    response.setContentType("image/png");

	    String code = request.getParameter("code");
	    String label = request.getParameter("label");

	    String message = "Le code du formulaire est " + code + ". Le libellé est " + label;

	    DataMatrixBean bean = new DataMatrixBean();
	    BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(response.getOutputStream(), "image/x-png", 300, BufferedImage.TYPE_BYTE_BINARY, false, 0);

	    bean.generateBarcode(canvasProvider, message);
	    canvasProvider.finish();
	}

}
