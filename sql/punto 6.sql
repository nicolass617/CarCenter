select a.*, sum(dm.cantidad) as cantidad_vendida from articulo a 
inner join detallemantenimiento dm
on dm.idArticulo = a.idArticulos
inner join mantenimiento m
on m.idMantenimiento = dm.idMantenimiento
where m.estado = 'TERMINADO' 
and m.estado2 = 'PAGADO'
and m.fechaFin between date_sub(curdate(), interval 30 day) and curdate()
order by cantidad_vendida desc limit 100;
