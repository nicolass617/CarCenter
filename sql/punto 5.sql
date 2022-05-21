select c.*, sum(a.precioUnitario)*dm.cantidad as acumulado from cliente c 
inner join mantenimiento m
on c.idCliente = m.idCliente
inner join detallemantenimiento dm
on dm.idMantenimiento = m.idMantenimiento
inner join articulo a
on a.idArticulos = dm.idArticulo
where m.fechaFin between date_sub(curdate(), interval 60 day) and curdate()
and (select(sum(a.precioUnitario)*dm.cantidad)) >= 100000
order by acumulado desc;