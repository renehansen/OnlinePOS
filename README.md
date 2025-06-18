# OnlinePOS Mini
## Architecture and code structure
The project follows the **MVVM** pattern, and a **Clean architecture** approach, ensuring a clear separation of concerns and testable design. The data flow is structured as follows:
**View -> ViewModel -> Domain -> Repository**

### Dependency Injection
Dependency inversion- and injection is used to promote a loosely coupled architecture and improve testability.

### Folder Structure
The project is organized into the following folders:

- **Core**: Contains shared functionality used across the app, such as translation handling, tracking, logging, reusable UI components, and other common utilities.
  - General app-related items (e.g., translation keys) are currently placed in the core folder. For reusability in other apps, the core folder could be split into `core` and `app-core`.

- **Features**: Since this is a small app, feature subfolders are placed directly in the root of the app. Each feature is divided into:
  - **Presentation**: Contains UI components and ViewModels for the feature.
  - **Domain**: Contains business logic and repository definitions.
  - **Data**: Contains repository implementations and data sources.

---

## UI
The UI design prioritizes flexibility and responsiveness. Key considerations include:

- **Constants for UI Values**: All UI-related values (e.g., colors, spacings, typography) are defined as constants to allow quick updates when exact details are provided by the designer.
- **Responsive Layout**:
  - The product groups column and cart have fixed widths.
  - The products column is responsive, resizing cards to fit. Currently, the layout assumes four columns of products, but an adaptive layout (where the number of columns is adjusted) could be implemented for smaller screens.
- **Material Design Icons**: Built-in Material Design icons are used, but in a real-world scenario, the app would use a custom icon library to ensure consistency.

### Developer Notes
I have intentionally been somewhat lax about the details of the UI. Usually, I would get information about colors, spacings, typography, etc. from Figma or the designer. I strongly believe that developers should refrain from guessing; it takes too long, and they will often guess wrong :D

- UI assumptions (e.g., handling overflowing text with fade-out or ellipsis) should be clarified with the designer.
- For this small project, UI components have just been created inline in the HomeScreen, but ordinarily I would advise to to define custom UI components in order to ensure consistency and prevent duplication of existing components.

---

## Testing

Unit tests have been added for the **Cart** and **ProductManager** to demonstrate test organization and methodology, including:
- **Equivalence Classes** and **Boundary Value Analysis** for identifying test cases.
- **Mocking External Dependencies** to isolate functionality.

### Known Issues
- Due to `build.gradle` problems, the `MockK` dependency could not be resolved, and tests for the `ProductManager` (which mock the repository) are currently commented out.

---

## Translations

Translations are implemented to support multiple languages, avoiding hardcoded texts. This ensures scalability and simplifies localization efforts.

---

## Cross-Platform and Hardware Issues

### Kotlin Multiplatform
- An attempt was made to implement shared UI using Kotlin Multiplatform, but it was abandoned due to time constraints and limited experience with the framework. This led to unresolved build.gradle issues, and the composables were subsequently relocated to the Android-specific folder.
- The `expect/actual` mechanism for loading translation files was also abandoned.

### MockK Dependency
- The `MockK` library could not be imported, preventing the use of mock tests.

### Testing Environment
- The app was tested exclusively in an Android simulator due to the lack of access to an Android tablet.

---

## Improvements
- Revisit Kotlin Multiplatform implementation for shared UI and translation handling.
- Resolve `MockK` dependency issues to enable mock testing.
- Clarify UI design details with the designer