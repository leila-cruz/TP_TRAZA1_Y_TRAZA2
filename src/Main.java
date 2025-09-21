import ClaseIntermedia.SucursalArticulo;
import Repositorio.*;
import Traza1Entidades.*;
import Traza2Entidades.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public class Main {
    public static void main(String[] args) {

        //-------------------------- TRAZA1 --------------------------

        InMemoryRepository<Empresa> repositorio = new InMemoryRepository<>();

        LocalTime horaApertura = LocalTime.of(9, 0, 0);
        LocalTime horaCierre = LocalTime.of(21, 0, 0);

        // ========================== PAÍSES ================================

        Pais argentina = Pais.builder()
                .id(1L)
                .nombre("Argentina")
                .build();

        //-------------------------- PROVINCIAS --------------------------

        Provincia bsas = Provincia.builder()
                .id(1L)
                .nombre("Buenos Aires")
                .pais(argentina)
                .build();

        Provincia cba = Provincia.builder()
                .id(2L)
                .nombre("Córdoba")
                .pais(argentina)
                .build();

        // -------------------------- LOCALIDADES --------------------------

        Localidad caba = Localidad.builder()
                .id(1L)
                .nombre("CABA")
                .provincia(bsas)
                .build();

        Localidad lp = Localidad.builder()
                .id(2L)
                .nombre("La Plata")
                .provincia(bsas)
                .build();

        Localidad ccp = Localidad.builder()
                .id(3L)
                .nombre("Córdoba Capital")
                .provincia(cba)
                .build();

        Localidad vcp = Localidad.builder()
                .id(4L)
                .nombre("Villa Carlos Paz")
                .provincia(cba)
                .build();

        //-------------------------- DOMICILIOS --------------------------

        Domicilio domcaba = Domicilio.builder()
                .id(1L)
                .calle("Pedrito Pedrero")
                .numero(3591)
                .cp(1000)
                .localidad(caba)
                .build();

        Domicilio domlp = Domicilio.builder()
                .id(2L)
                .calle("Cabrita Cabrero")
                .numero(1918)
                .cp(1900)
                .localidad(lp)
                .build();

        Domicilio domccp = Domicilio.builder()
                .id(3L)
                .calle("Loquito Loquero")
                .numero(49)
                .cp(5000)
                .localidad(ccp)
                .build();

        Domicilio domvcp = Domicilio.builder()
                .id(4L)
                .calle("Bolita Bolero")
                .numero(2121)
                .cp(5152)
                .localidad(vcp)
                .build();

        // -------------------------- SUCURSALES --------------------------

        Sucursal sucursal1 = Sucursal.builder()
                .id(1L)
                .nombre("Sucursal CABA")
                .horarioApertura(horaApertura)
                .horarioCierre(horaCierre)
                .es_Casa_Matriz(true)
                .domicilio(domcaba)
                .build();

        Sucursal sucursal2 = Sucursal.builder()
                .id(2L)
                .nombre("Sucursal La Plata")
                .horarioApertura(horaApertura)
                .horarioCierre(horaCierre)
                .domicilio(domlp)
                .build();

        Sucursal sucursal3 = Sucursal.builder()
                .id(3L)
                .nombre("Sucursal Córdoba Capital")
                .horarioApertura(horaApertura)
                .horarioCierre(horaCierre)
                .domicilio(domccp)
                .build();

        Sucursal sucursal4 = Sucursal.builder()
                .id(4L)
                .nombre("Sucursal Villa Carlos Paz")
                .horarioApertura(horaApertura)
                .horarioCierre(horaCierre)
                .domicilio(domvcp)
                .build();

        // -------------------------- EMPRESAS --------------------------

        Empresa empresa1 = Empresa.builder()
                .nombre("Mc Donalds")
                .razonSocial("McDonals SRL")
                .cuit(234234345)
                .logo("McDonalds.png")
                .build();

        Empresa empresa2 = Empresa.builder()
                .nombre("Burger King")
                .razonSocial("BurgerKing SRL")
                .cuit(1231236123)
                .logo("BK.png")
                .build();

        sucursal1.setEmpresa(empresa1);
        sucursal2.setEmpresa(empresa1);
        sucursal3.setEmpresa(empresa2);
        sucursal4.setEmpresa(empresa2);

        repositorio.guardar(empresa1);
        repositorio.guardar(empresa2);

        //-------------------------- TRAZA2 --------------------------

        InMemoryRepository<Categoria> categoriaRepository = new InMemoryRepository<>();
        InMemoryRepository<ArticuloInsumo> articuloInsumoRepository = new InMemoryRepository<>();
        InMemoryRepository<ArticuloManufacturado> articuloManufacturadoRepository = new InMemoryRepository<>();
        InMemoryRepository<UnidadMedida> unidadMedidaRepository = new InMemoryRepository<>();

        // -------------------------- CATEGORÍAS --------------------------

        Categoria hamburguesas = Categoria.builder().denominacion("Hamburguesas").esInsumo(false).build();
        Categoria acompanamientos = Categoria.builder().denominacion("Acompañamientos").esInsumo(false).build();
        Categoria bebidas = Categoria.builder().denominacion("Bebidas").esInsumo(false).build();
        Categoria insumos = Categoria.builder().denominacion("Insumos").esInsumo(true).build();

        // =-------------------------- UNIDADES MEDIDA --------------------------

        UnidadMedida unidad = UnidadMedida.builder().denominacion("Unidad").build();
        UnidadMedida kg = UnidadMedida.builder().denominacion("Kg").build();
        UnidadMedida litro = UnidadMedida.builder().denominacion("Litro").build();
        UnidadMedida gramos = UnidadMedida.builder().denominacion("Gramos").build();

        unidadMedidaRepository.guardar(unidad);
        unidadMedidaRepository.guardar(kg);
        unidadMedidaRepository.guardar(litro);
        unidadMedidaRepository.guardar(gramos);

        // -------------------------- ARTICULO INSUMO --------------------------

        ArticuloInsumo panHamburguesa = ArticuloInsumo.builder()
                .denominacion("Pan de Hamburguesa")
                .precioCompra(10.0)
                .stockActual(500)
                .stockMinimo(50)
                .stockMaximo(1000)
                .esParaElaborar(true)
                .unidadMedida(unidad)
                .build();

        ArticuloInsumo carne = ArticuloInsumo.builder()
                .denominacion("Carne Picada (Kg)")
                .precioCompra(800.0)
                .stockActual(100)
                .stockMinimo(10)
                .stockMaximo(300)
                .esParaElaborar(true)
                .unidadMedida(kg)
                .build();

        ArticuloInsumo papa = ArticuloInsumo.builder()
                .denominacion("Papa (Kg)")
                .precioCompra(80.0)
                .stockActual(200)
                .stockMinimo(20)
                .stockMaximo(500)
                .esParaElaborar(true)
                .unidadMedida(kg)
                .build();

        ArticuloInsumo aceite = ArticuloInsumo.builder()
                .denominacion("Aceite (Litro)")
                .precioCompra(300.0)
                .stockActual(50)
                .stockMinimo(5)
                .stockMaximo(100)
                .esParaElaborar(true)
                .unidadMedida(litro)
                .build();

        articuloInsumoRepository.guardar(panHamburguesa);
        articuloInsumoRepository.guardar(carne);
        articuloInsumoRepository.guardar(papa);
        articuloInsumoRepository.guardar(aceite);

        insumos.getArticulos().add(panHamburguesa);
        insumos.getArticulos().add(carne);
        insumos.getArticulos().add(papa);
        insumos.getArticulos().add(aceite);

        // -------------------------- IMAGEN ARTICULO --------------------------

        ImagenArticulo imgMcBurger1 = ImagenArticulo.builder().name("McBurger1").url("http://example.com/mcburger1").build();
        ImagenArticulo imgMcBurger2 = ImagenArticulo.builder().name("McBurger2").url("http://example.com/mcburger2").build();
        ImagenArticulo imgPapas1 = ImagenArticulo.builder().name("Papas1").url("http://example.com/papas1").build();
        ImagenArticulo imgWhopper1 = ImagenArticulo.builder().name("Whopper1").url("http://example.com/whopper1").build();
        ImagenArticulo imgPepsi1 = ImagenArticulo.builder().name("Pepsi1").url("http://example.com/pepsi1").build();

        // ========================== ARTICULO MANUFACTURADO DETALLE ================================

        ArticuloManufacturadoDetalle detPanHamburguesa = ArticuloManufacturadoDetalle.builder()
                .cantidad(1)
                .articuloInsumo(panHamburguesa)
                .build();

        ArticuloManufacturadoDetalle detCarneBurger = ArticuloManufacturadoDetalle.builder()
                .cantidad(1)
                .articuloInsumo(carne)
                .build();

        ArticuloManufacturadoDetalle detPapas = ArticuloManufacturadoDetalle.builder()
                .cantidad(1)
                .articuloInsumo(papa)
                .build();

        // -------------------------- ARTÍCULOS MANUFACTURADOS --------------------------

        // -------------------------- ARTÍCULOS MCDONADS --------------------------
        ArticuloManufacturado hamburguesaMc = ArticuloManufacturado.builder()
                .denominacion("Hamburguesa Clásica Mc")
                .precioVenta(1200.0)
                .descripcion("Hamburguesa clásica con lechuga y queso")
                .tiempoEstimadoMinutos(8)
                .preparacion("Cocinar la carne y armar el sándwich")
                .unidadMedida(unidad)
                .imagenes(new HashSet<>(Set.of(imgMcBurger1, imgMcBurger2)))
                .articuloManufacturadoDetalles(new HashSet<>(Set.of(detPanHamburguesa, detCarneBurger)))
                .build();

        ArticuloManufacturado papasFritas = ArticuloManufacturado.builder()
                .denominacion("Papas Fritas")
                .precioVenta(450.0)
                .descripcion("Porción de papas fritas")
                .tiempoEstimadoMinutos(5)
                .preparacion("Freír las papas")
                .unidadMedida(unidad)
                .imagenes(new HashSet<>(Set.of(imgPapas1)))
                .articuloManufacturadoDetalles(new HashSet<>(Set.of(detPapas)))
                .build();

        ArticuloManufacturado cocaCola = ArticuloManufacturado.builder()
                .denominacion("Coca Cola 500ml")
                .precioVenta(300.0)
                .descripcion("Bebida gaseosa")
                .tiempoEstimadoMinutos(0)
                .preparacion("Servir fría")
                .unidadMedida(unidad)
                .imagenes(new HashSet<>())
                .articuloManufacturadoDetalles(new HashSet<>())
                .build();

        // -------------------------- ARTÍCULOS BURGER KING --------------------------

        ArticuloManufacturado whopper = ArticuloManufacturado.builder()
                .denominacion("Whopper")
                .precioVenta(1400.0)
                .descripcion("Whopper clásico con tomate y lechuga")
                .tiempoEstimadoMinutos(10)
                .preparacion("Asar la carne y armar el sándwich")
                .unidadMedida(unidad)
                .imagenes(new HashSet<>(Set.of(imgWhopper1)))
                .articuloManufacturadoDetalles(new HashSet<>(Set.of(detPanHamburguesa, detCarneBurger)))
                .build();

        ArticuloManufacturado papasKing = ArticuloManufacturado.builder()
                .denominacion("Papas King")
                .precioVenta(480.0)
                .descripcion("Papas fritas estilo King")
                .tiempoEstimadoMinutos(6)
                .preparacion("Freír las papas")
                .unidadMedida(unidad)
                .imagenes(new HashSet<>(Set.of(imgPapas1)))
                .articuloManufacturadoDetalles(new HashSet<>(Set.of(detPapas)))
                .build();

        ArticuloManufacturado pepsi = ArticuloManufacturado.builder()
                .denominacion("Pepsi 500ml")
                .precioVenta(300.0)
                .descripcion("Bebida gaseosa")
                .tiempoEstimadoMinutos(0)
                .preparacion("Servir fría")
                .unidadMedida(unidad)
                .imagenes(new HashSet<>(Set.of(imgPepsi1)))
                .articuloManufacturadoDetalles(new HashSet<>())
                .build();

        articuloManufacturadoRepository.guardar(hamburguesaMc);
        articuloManufacturadoRepository.guardar(papasFritas);
        articuloManufacturadoRepository.guardar(cocaCola);
        articuloManufacturadoRepository.guardar(whopper);
        articuloManufacturadoRepository.guardar(papasKing);
        articuloManufacturadoRepository.guardar(pepsi);

        hamburguesas.getArticulos().add(hamburguesaMc);
        acompanamientos.getArticulos().add(papasFritas);
        bebidas.getArticulos().add(cocaCola);

        hamburguesas.getArticulos().add(whopper);
        acompanamientos.getArticulos().add(papasKing);
        bebidas.getArticulos().add(pepsi);

        categoriaRepository.guardar(hamburguesas);
        categoriaRepository.guardar(acompanamientos);
        categoriaRepository.guardar(bebidas);
        categoriaRepository.guardar(insumos);

        // -------------------------- VINCULAR ARTÍCULOS A SUCURSALES --------------------------

        SucursalArticulo sa1 = SucursalArticulo.builder()
                .id(1L)
                .sucursal(sucursal1) // Mc Donalds - CABA
                .articulo(hamburguesaMc)
                .stock(50)
                .build();

        SucursalArticulo sa2 = SucursalArticulo.builder()
                .id(2L)
                .sucursal(sucursal1) // Mc Donalds - CABA
                .articulo(papasFritas)
                .stock(80)
                .build();

        SucursalArticulo sa3 = SucursalArticulo.builder()
                .id(3L)
                .sucursal(sucursal2) // Mc Donalds - La Plata
                .articulo(hamburguesaMc)
                .stock(30)
                .build();

        SucursalArticulo sa4 = SucursalArticulo.builder()
                .id(4L)
                .sucursal(sucursal3) // Burger King - Córdoba Capital
                .articulo(whopper)
                .stock(40)
                .build();

        SucursalArticulo sa5 = SucursalArticulo.builder()
                .id(5L)
                .sucursal(sucursal3) // Burger King - Córdoba Capital
                .articulo(papasKing)
                .stock(60)
                .build();

        SucursalArticulo sa6 = SucursalArticulo.builder()
                .id(6L)
                .sucursal(sucursal4) // Burger King - Villa Carlos Paz
                .articulo(whopper)
                .stock(20)
                .build();

        sucursal1.agregarArticulo(sa1);
        sucursal1.agregarArticulo(sa2);
        sucursal2.agregarArticulo(sa3);
        sucursal3.agregarArticulo(sa4);
        sucursal3.agregarArticulo(sa5);
        sucursal4.agregarArticulo(sa6);

        // -------------------------- CONSOLA --------------------------

        System.out.println("\n---------------- MOSTRAR TODOS LOS MANUFACTURADOS ----------------");
        List<ArticuloManufacturado> manufacturados = articuloManufacturadoRepository.encontrarTodos();
        manufacturados.forEach(m -> System.out.println(
                "Manufacturado: " + m.getDenominacion() +
                        " | Precio Venta: $" + m.getPrecioVenta() +
                        " | Tiempo Estimado: " + m.getTiempoEstimadoMinutos() + " min"
        ));

        System.out.println("\n---------------- MOSTRAR TODOS LOS INSUMOS ----------------");
        List<ArticuloInsumo> insumosList = articuloInsumoRepository.encontrarTodos();
        insumosList.forEach(i -> System.out.println(
                "Insumo: " + i.getDenominacion() +
                        " | Stock Actual: " + i.getStockActual() +
                        " | Precio Compra: $" + i.getPrecioCompra()
        ));

        System.out.println("\n---------------- MOSTRAR STOCK EN TODAS LAS SUCURSALES ----------------");
        Set<SucursalArticulo> todasRelaciones = new HashSet<>();
        todasRelaciones.addAll(sucursal1.getArticulos());
        todasRelaciones.addAll(sucursal2.getArticulos());
        todasRelaciones.addAll(sucursal3.getArticulos());
        todasRelaciones.addAll(sucursal4.getArticulos());

        todasRelaciones.forEach(sa -> System.out.println(
                "Sucursal: " + sa.getSucursal().getNombre() +
                        " | Artículo: " + sa.getArticulo().getDenominacion() +
                        " | Precio Venta: $" + sa.getArticulo().getPrecioVenta() +
                        " | Stock en sucursal: " + sa.getStock()
        ));

        System.out.println("\n---------------- BUSCAR RELACIÓN SUCURSAL–ARTÍCULO POR ID ----------------");
        System.out.println("Buscando relación SucursalArticulo con ID = 2...");
        Optional<SucursalArticulo> relPorId = todasRelaciones.stream().filter(sa -> sa.getId().equals(2L)).findFirst();
        relPorId.ifPresentOrElse(
                sa -> System.out.println("Resultado: " + sa.getArticulo().getDenominacion() +
                        " disponible en " + sa.getSucursal().getNombre() + " | Stock: " + sa.getStock()),
                () -> System.out.println("Resultado: No se encontró la relación con ID 2")
        );

        System.out.println("\n---------------- BUSCAR ARTÍCULOS EN UNA SUCURSAL ----------------");
        String sucursalBuscada = "Sucursal CABA";
        System.out.println("Buscando artículos en la sucursal '" + sucursalBuscada + "'...");
        Set<SucursalArticulo> enSucursal = new HashSet<>();
        for (SucursalArticulo sa : todasRelaciones) {
            if (sa.getSucursal().getNombre().equalsIgnoreCase(sucursalBuscada)) {
                enSucursal.add(sa);
            }
        }
        if (enSucursal.isEmpty()) {
            System.out.println("Resultado: No hay artículos registrados en esa sucursal.");
        } else {
            enSucursal.forEach(sa -> System.out.println(
                    "Resultado: " + sa.getArticulo().getDenominacion() +
                            " | Precio: $" + sa.getArticulo().getPrecioVenta() +
                            " | Stock: " + sa.getStock()
            ));
        }

        System.out.println("\n---------------- ACTUALIZAR STOCK DE UN ARTÍCULO EN UNA SUCURSAL ----------------");
        System.out.println("Actualizando stock de la relación con ID = 1...");
        Optional<SucursalArticulo> relToUpdate = todasRelaciones.stream().filter(sa -> sa.getId().equals(1L)).findFirst();
        if (relToUpdate.isPresent()) {
            SucursalArticulo sa = relToUpdate.get();
            sa.setStock(120);
            System.out.println("Resultado: Relación después de la actualización -> Sucursal: " + sa.getSucursal().getNombre()
                    + " | Artículo: " + sa.getArticulo().getDenominacion()
                    + " | Stock: " + sa.getStock());
        } else {
            System.out.println("Resultado: No se encontró la relación con ID 1");
        }

        System.out.println("\n----------------- ELIMINAR RELACIÓN ARTÍCULO–SUCURSAL ----------------");
        System.out.println("Eliminando relación con ID = 6...");
        boolean eliminado = false;
        Set<Sucursal> todasSucursales = new HashSet<>(Set.of(sucursal1, sucursal2, sucursal3, sucursal4));
        for (Sucursal s : todasSucursales) {
            eliminado = s.getArticulos().removeIf(sa -> sa.getId().equals(6L));
            if (eliminado) {
                System.out.println("Resultado: La relación artículo–sucursal con ID 6 ha sido eliminada correctamente de la sucursal " + s.getNombre());
                break;
            }
        }
        if (!eliminado) {
            System.out.println("Resultado: No se encontró la relación con ID 6");
        }
    }
}
