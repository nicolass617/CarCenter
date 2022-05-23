select t.nombre, a.nombre, sum(dm.idArticulo) as cantidad from tienda t
inner join mantenimiento m
on t.idTienda = m.idTienda
inner join detallemantenimiento dm
on m.idMantenimiento = dm.idMantenimiento
inner join articulo a 
on a.idArticulos = dm.idArticulo
where (select sum(dm.idArticulo)) > 100
and a.codigo = 100
and m.estado2 = 'FACTURADO';