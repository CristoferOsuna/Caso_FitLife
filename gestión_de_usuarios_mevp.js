const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');

const JWT_SECRET = 'mi_clave_super_secreta_para_desarrollo_123';

let usersDb = [];
let passwordResetStore = new Map();

class UserManager {

    async registrarUsuario(nombre, email, passwordPlano) {
        const existe = usersDb.find(u => u.email === email);
        if (existe) {
            throw new Error("Error: El correo electrónico ya está registrado.");
        }

        const salt = await bcrypt.genSalt(10);
        const passwordHash = await bcrypt.hash(passwordPlano, salt);

        const nuevoUsuario = {
            id_usuario: usersDb.length + 1,
            nombre: nombre,
            email: email,
            password_hash: passwordHash,
            fecha_registro: new Date(),
            perfil: {
                telefono: null,
                direccion: null,
                foto_url: null
            }
        };

        usersDb.push(nuevoUsuario);
        console.log(`[REGISTRO] Usuario ${email} creado con ID ${nuevoUsuario.id_usuario}`);

        return { id: nuevoUsuario.id_usuario, email: nuevoUsuario.email };
    }

    async iniciarSesion(email, passwordPlano) {
        const usuario = usersDb.find(u => u.email === email);
        if (!usuario) {
            throw new Error("Credenciales inválidas.");
        }

        const contraseñaValida = await bcrypt.compare(passwordPlano, usuario.password_hash);
        if (!contraseñaValida) {
            throw new Error("Credenciales inválidas.");
        }

        const token = jwt.sign(
            { id_usuario: usuario.id_usuario, email: usuario.email },
            JWT_SECRET,
            { expiresIn: '1h' }
        );

        console.log(`[LOGIN] Usuario ${email} ha iniciado sesión.`);
        return { token: token, mensaje: "Inicio de sesión exitoso" };
    }

    async actualizarPerfil(idUsuario, nuevosDatosPerfil) {
        const usuario = usersDb.find(u => u.id_usuario === idUsuario);
        if (!usuario) throw new Error("Usuario no encontrado.");

        usuario.perfil = { ...usuario.perfil, ...nuevosDatosPerfil };

        console.log(`[PERFIL] Perfil actualizado para ID ${idUsuario}`);
        return usuario.perfil;
    }

    async solicitarRecuperacionPass(email) {
        const usuario = usersDb.find(u => u.email === email);
        if (!usuario) throw new Error("Correo no encontrado.");

        const resetToken = Math.random().toString(36).substring(2, 15);
        const expiracion = Date.now() + 3600000;

        passwordResetStore.set(email, { token: resetToken, expira: expiracion });

        console.log("\n--- [SIMULACIÓN DE EMAIL] ---");
        console.log(`Para: ${email}`);
        console.log(`Asunto: Recuperación de contraseña`);
        console.log(`Tu código de recuperación es: ${resetToken}`);
        console.log("-----------------------------\n");

        return { mensaje: "Revisa tu correo electrónico (consola) para el código de recuperación." };
    }

    async restablecerPassword(email, resetToken, nuevaPasswordPlana) {
        const datosToken = passwordResetStore.get(email);

        if (!datosToken || datosToken.token !== resetToken || Date.now() > datosToken.expira) {
            throw new Error("El token de recuperación es inválido o ha expirado.");
        }

        const usuario = usersDb.find(u => u.email === email);

        const salt = await bcrypt.genSalt(10);
        const nuevoHash = await bcrypt.hash(nuevaPasswordPlana, salt);

        usuario.password_hash = nuevoHash;

        passwordResetStore.delete(email);

        console.log(`[PASSWORD] Contraseña restablecida para ${email}`);
        return { mensaje: "Contraseña actualizada correctamente." };
    }

    verificarTokenSesion(token) {
        try {
            const decoded = jwt.verify(token, JWT_SECRET);
            return decoded;
        } catch (error) {
            throw new Error("Sesión inválida o expirada.");
        }
    }
}

(async () => {
    const userManager = new UserManager();
    const emailDemo = "juan@ejemplo.com";
    const passDemo = "Pass1234";

    console.log(">>> INICIANDO DEMO DE GESTIÓN DE USUARIOS <<< \n");

    try {
        console.log("1. Intentando registrar usuario...");
        await userManager.registrarUsuario("Juan Perez", emailDemo, passDemo);

        console.log("\n2. Intentando iniciar sesión...");
        const loginResult = await userManager.iniciarSesion(emailDemo, passDemo);
        const miTokenDeSesion = loginResult.token;

        console.log("\n3. Intentando actualizar perfil (ruta protegida)...");
        const datosSesion = userManager.verificarTokenSesion(miTokenDeSesion);
        const perfilActualizado = await userManager.actualizarPerfil(datosSesion.id_usuario, {
            telefono: "555-0199",
            direccion: "Calle Falsa 123"
        });
        console.log("Perfil resultante:", perfilActualizado);

        console.log("\n4. Flujo de recuperación de contraseña...");
        await userManager.solicitarRecuperacionPass(emailDemo);
        const tokenSimulado = passwordResetStore.get(emailDemo).token;

        console.log("Aplicando nueva contraseña...");
        await userManager.restablecerPassword(emailDemo, tokenSimulado, "NuevaPassSuperSegura_99");

        console.log("\n5. Probando inicio de sesión con la NUEVA contraseña...");
        await userManager.iniciarSesion(emailDemo, "NuevaPassSuperSegura_99");

    } catch (error) {
        console.error("\n!!! ERROR EN EL FLUJO !!!");
        console.error(error.message);
    }
})();


