<nav id="sidebar">
    <div id="dismiss">
        <i class="fas fa-arrow-left"></i>
    </div>

    <div class="sidebar-header">
        <h3>GAMERS PLANET</h3>
        <img src="logo.png" class="avatar" alt="Avatar Image">
    </div>

    <ul class="list-unstyled components">
        <p>Play and enjoy</p>
        <li>
            <a style="text-decoration: none;" href="menuGamers.jsp">Home</a>                    
        </li>
        <li>
            <a style="text-decoration: none;" href="perfilUsuario.jsp">Perfil usuario</a>
        </li>
        <li>
            <a style="text-decoration: none;" href="#homeSubmenu" data-toggle="collapse" aria-expanded="false">Catalagos</a> 
            <ul class="collapse list-unstyled" id="homeSubmenu">
                <li>
                    <a style="text-decoration: none;" href="crudUsuario.jsp">Usuario</a>
                </li>
                <li>
                    <a style="text-decoration: none;" href="ClienteServlet">Cliente</a>
                </li>
                <li>
                    <a style="text-decoration: none;" href="ControllProdcutoCRUD">Producto</a>
                </li>
            </ul>
        </li>
        <li>
            <a style="text-decoration: none;" href="ControllProducts">Compra productos</a>                    
        </li>
        <li>
            <a style="text-decoration: none;" href="movimientos.jsp">Movimientos Salidas</a>                    
        </li>
        <li>
            <a style="text-decoration: none;" href="movimientoEntrada.jsp">Movimientos Entrada</a>                    
        </li>
        <li>
            <a style="text-decoration: none;" href="acercaDe.jsp">Acerca de</a>
        </li>
        <li>
            <a style="text-decoration: none;" href="contactos.jsp">Contactanos</a>
        </li>
    </ul>

    <ul class="list-unstyled CTAs">
        <li>
            <a style="text-decoration: none;" href="LogOutServlet" class="download">Log out</a>
        </li>
    </ul>
</nav>