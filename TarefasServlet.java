import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/tarefas")
public class TarefasServlet extends HttpServlet {

	private static final long serialVersionUID = 4228614128169546881L;
	private List<Tarefa> tarefas = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("tarefas", tarefas);
        request.getRequestDispatcher("/listaTarefas.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String descricao = request.getParameter("descricao");

        if (descricao != null && !descricao.isEmpty()) {
            Tarefa novaTarefa = new Tarefa();
            tarefas.add(novaTarefa);
        }

        String acao = request.getParameter("acao");
        int indice = Integer.parseInt(request.getParameter("indice"));

        if ("editar".equals(acao)) {
            Tarefa tarefaEditada = tarefas.get(indice);
            tarefaEditada.setDescricao(descricao);
        } else if ("excluir".equals(acao)) {
            tarefas.remove(indice);
        } else if ("concluir".equals(acao)) {
            Tarefa tarefaConcluida = tarefas.get(indice);
            tarefaConcluida.setConcluida(true);
        }

        response.sendRedirect(request.getContextPath() + "/tarefas");
    }
}

