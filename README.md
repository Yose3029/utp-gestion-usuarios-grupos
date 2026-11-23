# Gestión de Usuarios, Grupos y Permisos de Archivos en Linux

Material y documentación sobre administración de cuentas, grupos, control de acceso y permisos en sistemas Linux bajo el principio de **mínimo privilegio**.

## Contenido cubierto
* **Identidades y archivos del sistema:** Uso de `UID` y `GID` (incluyendo `root` con UID `0` y elevación controlada con `sudo`), junto con `/etc/passwd`, `/etc/shadow`, `/etc/group` y `/etc/gshadow`.
* **Gestión de usuarios y grupos:** Comandos como `useradd`, `passwd`, `usermod` (`-aG`), `userdel` (`-r`), `id`, `groupadd`, `gpasswd`, `groups`, `groupmod`, `newgrp` y `groupdel`.
* **Permisos y propiedad:** Interpretación de `ls -l` (`u`, `g`, `o` y bits `r`, `w`, `x` en archivo vs. directorio) y uso de `chmod`, `chown` y `chgrp` evitando el uso indiscriminado de `chmod 777`.
* **Permisos especiales y caso práctico:** `setuid` (`4`), `setgid` (`2`) y `sticky bit` (`1`), además de la configuración de un directorio colaborativo en `/srv/proyecto` con `chmod 2770`.
* **Diagnóstico de incidencias:** Resolución de errores frecuentes como `Permission denied`, `cd: denied`, grupos no visibles o desactualizados, y archivos residuales al eliminar cuentas.

## Integrantes del trabajo
* David Ureña
* Yoselin Pérez
* Luis Montenegro
* Oliver Oses

## Enlaces
* **Perfil de GitHub:** [https://github.com/Yose3029](https://github.com/Yose3029)
* **GitHub Pages:** [https://yose3029.github.io/utp-gestion-usuarios-grupos/](https://yose3029.github.io/utp-gestion-usuarios-grupos/)
* **LinkedIn:** [https://www.linkedin.com/in/yoselin-perez-565b0a435](https://www.linkedin.com/in/yoselin-perez-565b0a435)
