import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Principal {

	public static final String URL_GET = "https://imdb-api.com/en/API/Top250Movies/";

	public static void main(String[] args) throws Exception {

		try {
			// Criação do cliente HTTP
			HttpClient client = HttpClient.newBuilder().build();

			// Criação da requisição
			HttpRequest request = HttpRequest.newBuilder()
					.GET()
					.timeout(Duration.ofSeconds(10))
					.uri(URI.create(URL_GET))
					.build();

			// Envio da solicitação
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			System.out.print(response.body().replace("{", "\n"));

			// Criação de um arquivo .txt, com o conteúdo do JSON e formatação de
			// texto do windows
			PrintWriter ps = new PrintWriter("top250Movies.txt", "windows-1252");
			ps.println(response.body().replace("{", "\n"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}