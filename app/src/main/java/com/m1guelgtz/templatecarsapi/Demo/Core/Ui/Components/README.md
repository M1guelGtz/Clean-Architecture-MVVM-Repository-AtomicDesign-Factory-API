# Diseño Atómico - Template Cars API

Este proyecto implementa el patrón de **Diseño Atómico** para crear componentes de UI reutilizables y escalables.

## Estructura de Componentes

### 📦 Atoms (Átomos)
Componentes básicos e indivisibles de la UI.

**Ubicación:** `Demo/Core/Ui/Components/Atoms/`

- **AppText.kt**: Componentes de texto reutilizables
  - `AppText()` - Texto base configurable
  - `TitleText()` - Títulos grandes y destacados
  - `SubtitleText()` - Subtítulos y texto secundario
  - `BodyText()` - Texto de cuerpo

- **AppButton.kt**: Botones reutilizables
  - `AppButton()` - Botón principal con estilos
  - `AppOutlinedButton()` - Botón con borde
  - `AppTextButton()` - Botón de texto simple

- **AppIcon.kt**: Iconos y elementos visuales
  - `AppIcon()` - Icono básico configurable
  - `CircularIcon()` - Icono dentro de círculo
  - `StatusIndicator()` - Indicador de estado (activo/inactivo)

- **AppSpacer.kt**: Espaciadores para layout
  - `VerticalSpacer()` - Espacio vertical
  - `HorizontalSpacer()` - Espacio horizontal
  - `SmallSpacer()`, `MediumSpacer()`, `LargeSpacer()`, `ExtraLargeSpacer()` - Tamaños predefinidos

---

### 🧩 Molecules (Moléculas)
Combinaciones de átomos que forman componentes funcionales.

**Ubicación:** `Demo/Core/Ui/Components/Molecules/`

- **SearchBar.kt**: Componentes de búsqueda
  - `SearchBar()` - Barra de búsqueda completa con iconos
  - `AppTextField()` - Campo de texto configurable

- **StateComponents.kt**: Componentes de estados
  - `LoadingIndicator()` - Indicador de carga
  - `ErrorMessage()` - Mensaje de error
  - `EmptyState()` - Estado vacío

- **AppCard.kt**: Cards reutilizables
  - `BaseCard()` - Card base configurable
  - `InfoCard()` - Card con título, subtítulo y elementos adicionales

---

### 🏗️ Organisms (Organismos)
Componentes complejos que combinan moléculas y átomos.

**Ubicación:** `Demo/Core/Ui/Components/Organisms/`

- **AppTopBar.kt**: Barra superior de la app
  - `AppTopBar()` - TopBar con título, botón back y acciones

---

## Uso de Componentes

### Ejemplo 1: Crear un Card personalizado

```kotlin
@Composable
fun MyCustomCard() {
    InfoCard(
        title = "Mi Título",
        subtitle = "Mi subtítulo",
        leading = {
            CircularIcon(
                imageVector = Icons.Default.Person,
                contentDescription = "Usuario"
            )
        },
        trailing = {
            AppButton(
                text = "Acción",
                onClick = { /* hacer algo */ }
            )
        },
        onClick = { /* click en el card */ }
    )
}
```

### Ejemplo 2: Usar componentes de texto

```kotlin
@Composable
fun MyScreen() {
    Column {
        TitleText(text = "Bienvenido")
        MediumSpacer()
        SubtitleText(text = "Esta es una descripción")
        SmallSpacer()
        BodyText(text = "Texto adicional de cuerpo")
    }
}
```

### Ejemplo 3: Estados de la UI

```kotlin
@Composable
fun ContentScreen(isLoading: Boolean, error: String?, data: List<Item>) {
    when {
        isLoading -> LoadingIndicator()
        error != null -> ErrorMessage(message = error)
        data.isEmpty() -> EmptyState(message = "No hay datos")
        else -> {
            // Mostrar datos
        }
    }
}
```

---

## Beneficios del Diseño Atómico

✅ **Reutilización**: Componentes pueden ser usados en cualquier parte de la app
✅ **Consistencia**: Estilos y comportamientos uniformes
✅ **Mantenibilidad**: Cambios centralizados afectan toda la app
✅ **Escalabilidad**: Fácil agregar nuevos componentes
✅ **Testing**: Componentes pequeños son más fáciles de testear
✅ **Documentación**: Estructura clara y organizada

---

## Convenciones de Nomenclatura

- **Atoms**: Prefijo `App` + nombre del componente (ej: `AppButton`, `AppText`)
- **Molecules**: Nombre descriptivo de la función (ej: `SearchBar`, `InfoCard`)
- **Organisms**: Prefijo `App` + nombre del organismo (ej: `AppTopBar`, `AppDrawer`)

---

## Estructura de Carpetas

```
Demo/
└── Core/
    └── Ui/
        └── Components/
            ├── Atoms/          # Componentes básicos
            ├── Molecules/      # Combinaciones simples
            └── Organisms/      # Componentes complejos
```

---

## Próximos Pasos

- [ ] Agregar más iconos personalizados
- [ ] Crear componentes de navegación (Bottom Navigation, Drawer)
- [ ] Implementar sistema de temas
- [ ] Agregar animaciones a componentes
- [ ] Crear biblioteca de colores y tipografías
