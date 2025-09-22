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

        //-------------------------- PAÍSES --------------------------

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
                .calle("Martin Zapata")
                .numero(3591)
                .cp(1000)
                .localidad(caba)
                .build();

        Domicilio domlp = Domicilio.builder()
                .id(2L)
                .calle("Olascoaga")
                .numero(1918)
                .cp(1900)
                .localidad(lp)
                .build();

        Domicilio domccp = Domicilio.builder()
                .id(3L)
                .calle("Juan B. Justo")
                .numero(49)
                .cp(5000)
                .localidad(ccp)
                .build();

        Domicilio domvcp = Domicilio.builder()
                .id(4L)
                .calle("Martinez de Rozas")
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

        Categoria pizzas = Categoria.builder().denominacion("Pizzas").esInsumo(false).build();
        Categoria sandwich = Categoria.builder().denominacion("Sandwich").esInsumo(false).build();
        Categoria lomos = Categoria.builder().denominacion("Lomos").esInsumo(false).build();
        Categoria insumos = Categoria.builder().denominacion("Insumos").esInsumo(true).build();

// -------------------------- UNIDADES MEDIDA --------------------------

        UnidadMedida kg = UnidadMedida.builder().denominacion("Kilogramos").build();
        UnidadMedida litro = UnidadMedida.builder().denominacion("Litros").build();
        UnidadMedida gramos = UnidadMedida.builder().denominacion("Gramos").build();

        unidadMedidaRepository.guardar(kg);
        unidadMedidaRepository.guardar(litro);
        unidadMedidaRepository.guardar(gramos);

// -------------------------- ARTICULO INSUMO --------------------------

        ArticuloInsumo sal = ArticuloInsumo.builder()
                .denominacion("Sal")
                .precioCompra(50.0)
                .stockActual(100)
                .stockMinimo(10)
                .stockMaximo(300)
                .esParaElaborar(true)
                .unidadMedida(gramos)
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

        ArticuloInsumo carne = ArticuloInsumo.builder()
                .denominacion("Carne (Kg)")
                .precioCompra(800.0)
                .stockActual(100)
                .stockMinimo(10)
                .stockMaximo(300)
                .esParaElaborar(true)
                .unidadMedida(kg)
                .build();

        ArticuloInsumo harina = ArticuloInsumo.builder()
                .denominacion("Harina (Kg)")
                .precioCompra(200.0)
                .stockActual(150)
                .stockMinimo(20)
                .stockMaximo(400)
                .esParaElaborar(true)
                .unidadMedida(kg)
                .build();

        articuloInsumoRepository.guardar(sal);
        articuloInsumoRepository.guardar(aceite);
        articuloInsumoRepository.guardar(carne);
        articuloInsumoRepository.guardar(harina);

        insumos.getArticulos().add(sal);
        insumos.getArticulos().add(aceite);
        insumos.getArticulos().add(carne);
        insumos.getArticulos().add(harina);

// -------------------------- IMAGEN ARTICULO --------------------------

        ImagenArticulo imgPizza1 = ImagenArticulo.builder().name("HawainaPizza1").url("http://example.com/hawaina1").build();
        ImagenArticulo imgPizza2 = ImagenArticulo.builder().name("HawainaPizza2").url("http://example.com/hawaina2").build();
        ImagenArticulo imgPizza3 = ImagenArticulo.builder().name("HawainaPizza3").url("http://example.com/hawaina3").build();

        ImagenArticulo imgLomo1 = ImagenArticulo.builder().name("LomoCompletoLomo1").url("http://example.com/lomo1").build();
        ImagenArticulo imgLomo2 = ImagenArticulo.builder().name("LomoCompletoLomo2").url("http://example.com/lomo2").build();
        ImagenArticulo imgLomo3 = ImagenArticulo.builder().name("LomoCompletoLomo3").url("http://example.com/lomo3").build();

// ========================== ARTICULO MANUFACTURADO DETALLE ================================

        ArticuloManufacturadoDetalle detallePizzaHawaina1 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1)
                .articuloInsumo(sal)
                .build();

        ArticuloManufacturadoDetalle detallePizzaHawaina2 = ArticuloManufacturadoDetalle.builder()
                .cantidad(2)
                .articuloInsumo(harina)
                .build();

        ArticuloManufacturadoDetalle detallePizzaHawaina3 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1)
                .articuloInsumo(aceite)
                .build();

        ArticuloManufacturadoDetalle detalleLomoCompleto1 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1)
                .articuloInsumo(sal)
                .build();

        ArticuloManufacturadoDetalle detalleLomoCompleto2 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1)
                .articuloInsumo(aceite)
                .build();

        ArticuloManufacturadoDetalle detalleLomoCompleto3 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1)
                .articuloInsumo(carne)
                .build();

// -------------------------- ARTÍCULOS MANUFACTURADOS --------------------------

        ArticuloManufacturado pizzaHawaina = ArticuloManufacturado.builder()
                .denominacion("Pizza Hawaina")
                .precioVenta(1800.0)
                .descripcion("Pizza Hawaina con jamón, queso y ananá")
                .tiempoEstimadoMinutos(15)
                .preparacion("Preparar la masa, colocar ingredientes y hornear")
                .unidadMedida(gramos)
                .imagenes(new HashSet<>(Set.of(imgPizza1, imgPizza2, imgPizza3)))
                .articuloManufacturadoDetalles(new HashSet<>(Set.of(detallePizzaHawaina1, detallePizzaHawaina2, detallePizzaHawaina3)))
                .build();

        ArticuloManufacturado lomoCompleto = ArticuloManufacturado.builder()
                .denominacion("Lomo Completo")
                .precioVenta(1500.0)
                .descripcion("Lomo completo con carne, huevo, jamón y queso")
                .tiempoEstimadoMinutos(12)
                .preparacion("Cocinar la carne y armar el lomo con sus ingredientes")
                .unidadMedida(gramos)
                .imagenes(new HashSet<>(Set.of(imgLomo1, imgLomo2, imgLomo3)))
                .articuloManufacturadoDetalles(new HashSet<>(Set.of(detalleLomoCompleto1, detalleLomoCompleto2, detalleLomoCompleto3)))
                .build();

        articuloManufacturadoRepository.guardar(pizzaHawaina);
        articuloManufacturadoRepository.guardar(lomoCompleto);

        pizzas.getArticulos().add(pizzaHawaina);
        lomos.getArticulos().add(lomoCompleto);

        categoriaRepository.guardar(pizzas);
        categoriaRepository.guardar(lomos);
        categoriaRepository.guardar(insumos);
        categoriaRepository.guardar(sandwich);

// -------------------------- VINCULAR ARTÍCULOS A SUCURSALES --------------------------

        SucursalArticulo sa1 = SucursalArticulo.builder()
                .id(1L)
                .sucursal(sucursal1)
                .articulo(pizzaHawaina)
                .stock(20)
                .build();

        SucursalArticulo sa2 = SucursalArticulo.builder()
                .id(2L)
                .sucursal(sucursal1)
                .articulo(lomoCompleto)
                .stock(15)
                .build();

        sucursal1.agregarArticulo(sa1);
        sucursal1.agregarArticulo(sa2);
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
