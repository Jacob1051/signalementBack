<%@page import="data.Signalement,data.Region,java.util.ArrayList" %>
<%
    int pagination = 10;
    ArrayList<Signalement> listes = (ArrayList<Signalement>)request.getAttribute("listes");
    ArrayList<Region> listeRegion = (ArrayList<Region>)request.getAttribute("listeRegion");
    int nbPage = Integer.parseInt(request.getAttribute("nbSignalement")+"");
%>

<div class="container-fluid py-4">
        <div class="col-lg-12 col-md-10 mx-auto">
          <div class="card">
            <div class="card-header pb-0">
              <div class="row">
                <div class="col-lg-6 col-7">
                  <h6>Affectation des Signalements</h6>
                </div>
              </div>
            </div>
            <div class="card-body px-0 pb-2">
              <div class="table-responsive">
                <form action="${pageContext.request.contextPath}/Affectation" method="POST">
                <table class="table align-items-center mb-0">
                  <thead>
                    <tr>
                      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Categorie</th>
                      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Description</th>
                      <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Action</th>
                    </tr>
                  </thead>
                  <tbody>
                    <%
                        for(int i=0; i<listes.size(); i++) {
                    %>
                    <tr>
                      <td>
                        <div class="d-flex px-2 py-1">
                          <div class="d-flex flex-column justify-content-center">
                            <h6 class="mb-0 text-sm"><%= listes.get(i).getNomCategory()%></h6>
                          </div>
                        </div>
                      </td>
                      <td>
                            <%= listes.get(i).getDescription()%>
                      </td>
                      <td class="align-middle">
                        <div class="col-lg-3 col-sm-3 col-3">
                            <select name="<%= listes.get(i).getId()%>_report">
                                <%
                                    for(int ii=0; ii<listeRegion.size(); ii++){
                                %>
                                <option value="<%=listeRegion.get(ii).getId()%>"><%=listeRegion.get(ii).getNom()%></option>
                                <%
                                    }
                                %>
                            </select>
                        </div>
                      </td>
                    </tr>
                    <%
                        }
                    %>
                    <tr>
                        <td colspan="3"><input class="btn bg-gradient-success w-100 mb-0" type="submit" value="Valider"></td>
                    </tr>
                  </tbody>
                </table>
                </form>
              </div>
            </div>
          </div>
        </div>
        </br>
        <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}/pages/home.jsp?onglet=3&pages=<%=((Integer.parseInt(request.getParameter("pages"))-1)<0)?request.getParameter("pages"):(Integer.parseInt(request.getParameter("pages"))-1)%>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <%  
                int neededPages = (int)Math.ceil(nbPage/10d);
                for(int iii=0; iii<neededPages; iii++) { 
            %>
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/pages/home.jsp?onglet=3&pages=<%=iii%>"><%=iii+1%></a></li>
            <% } %>
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}/pages/home.jsp?onglet=3&pages=<%=((Integer.parseInt(request.getParameter("pages"))+1)>=neededPages)?request.getParameter("pages"):(Integer.parseInt(request.getParameter("pages"))+1)%>" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
                    
      </div>
                    
    
    
    <div class="container-fluid py-4">
      <footer class="footer py-4  ">
        <div class="container-fluid">
          <div class="row align-items-center justify-content-lg-between">
            <div class="col-lg-6 mb-lg-0 mb-4">
              <div class="copyright text-center text-sm text-muted text-lg-start">
                © <script>
                  document.write(new Date().getFullYear())
                </script>,
                made with <i class="fa fa-heart"></i> by
                <a href="https://www.creative-tim.com" class="font-weight-bold" target="_blank">Creative Tim</a>
                for a better web.
              </div>
            </div>
            <div class="col-lg-6">
              <ul class="nav nav-footer justify-content-center justify-content-lg-end">
                <li class="nav-item">
                  <a href="https://www.creative-tim.com" class="nav-link text-muted" target="_blank">Creative Tim</a>
                </li>
                <li class="nav-item">
                  <a href="https://www.creative-tim.com/presentation" class="nav-link text-muted" target="_blank">About Us</a>
                </li>
                <li class="nav-item">
                  <a href="https://www.creative-tim.com/blog" class="nav-link text-muted" target="_blank">Blog</a>
                </li>
                <li class="nav-item">
                  <a href="https://www.creative-tim.com/license" class="nav-link pe-0 text-muted" target="_blank">License</a>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </footer>
    </div>