## Architecture and code structure
I have used MVVM, and a clean architecture where data flows in this direction:
View -> ViewModel -> Domain -> Repository

I use dependency injection in order to ensure a loosely coupled, and testable design

The overall folder structure is as follows:
- Core: Contains the core functionality of the app. This includes translation handling (implemented), tracking, logging, reusable UI components, and other common functionality that is used across the app.
  - Currently, I have placed some general app-related stuff (like Translation keys) in the core folder. If we wanted to reuse core in other apps, I would separate core from app-core. 
- Ordinarily I would then have a "features" folder, where each feature has its own folder, but since this is a small app, I have put the feature subfolders directly in the root of the app.
  - Presentation: Contains the UI components and UI logic (ViewModels) for the feature.
  - Domain: Contains the business logic for the feature. It also contains the repository definitions, which are implemented in the data layer.
  - Data: Contains the data layer for the feature, which includes the repository implementations and any data sources.

## UI

I have intentionally been somewhat lax about the details of the UI. Usually, I would get information about colors, spacings, typography, etc. from Figma or the designer. I strongly believe that developers should refrain from guessing - it takes too long, and they will often guess wrong :D 
However, all values have been created as constants instead of using hardcoded values, so that the UI can quickly be updated when I get the exact details from the UI designer.

The product groups column and cart have fixed widths, while the products column is design to be responsive by resizing the cards to fit. I have made the assumption that we should stick with four columns of products, but we could also consider an adaptive layout that reduces the number of columns on smaller screens.

In a normal work-situation I of course wouldn't assume anything - I would clarify with the UI designer what the intention is, and how the UI should respond to changing screen sizes. 

A couple of additional things that I would normally ask for clarification about: 
- How should overflowing texts be handled (e.g., fade out or ellipsis)
- Here, I have used built-in icons from the Material Design library, but I would assume that OnlinePOS has its own icon library, so I would stick to that in a real project in order to avoid inconsistencies in the UI

I have created all UI components inline in the HomeScreen. However, I would always strongly suggest that we define our own custom components in order to ensure a consistent UI (and in order to prevent people from reinventing already existing components).

## Testing
I have added unit of the Cart and ProductManager tests to demonstrate how I would typically organize and write tests (identify appropriate test cases via equivalence classes and boundary value analysis as well as mocking external dependencies).
However, due to build.gradle problems, mockk couldn't be resolved, so the ProductManager tests - where I wanted to mock out the (already mocked :D ) repository - are currently commented out.

## Translations
  I have added translations, rather than hardcoding texts, in order to make it easy to support multiple languages. I would always establish this as soon as possible in order to avoid non-translated texts in the app.

## Cross-platform- and hardware issues
Due to the fact that I haven't worked with Kotlin Multiplatform before, I spent WAY too much time trying to get the shared UI to work. Due to time constraints, I had to give up on that, and I instead moved the composables to the Android part.
I also had to abandon using expect/actual to load in the translation files.
Finally, as mentioned, I also wasn't able to import mockk, so that mock tests are currently commented out.

I don't have an Android tablet, so everything has been tested in a simulator.