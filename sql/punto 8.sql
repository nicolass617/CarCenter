select c.primerNombre as nombre, 
c.primerApellido as apellido, 
c.documento as numero_de_documento, 
sum(m.idMantenimiento) as cantidad_mantenimientos 
from cliente c
inner join mantenimiento m
on m.idCliente = c.idCliente
where (select sum(m.idMantenimiento)) >= 1
and m.fechaFin between date_sub(curdate(), interval 60 day) and curdate();