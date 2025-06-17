## UI
The product groups column and cart have fixed widths, while the products column is design to be responsive by resizing the cards to fit. I have made the assumption that we should stick with four columns of products, but we could also consider an adaptive layout that reduces the number of columns on smaller screens. I have assumed that the padding between the cards remain fixed.

I have intentionally been somewhat lax about the details of the UI: colors (I am also color blind :D), spacings, typography. Usually, I would get these values from Figma; otherwise I would query the designer, instead of spending time guessing the values - and risk guessing wrong.  
All values have been created as constants instead of using hardcoded values, which means that they can quickly be changed.

In a normal work-situation I of course wouldn't assume anything - I would clarify with the UI designer what the intention is, and how the UI should respond to changing screen sizes. 

A couple of additional things that I would normally ask for clarification about: 
- How should overflowing texts be handled (e.g., fade out or ellipsis)
- Should we make the UI adaptive, such that the number of columns displaying the products decrease for small tablet sizes
- Here, I have used built-in icons from the Material Design library, but I would assume that OnlinePOS has its own icon library, so I would stick to that in a real project in order to avoid inconsistencies in the UI

I have created all UI components inline in the HomeScreen component. Usually, I would expect that allowed UI elements are defined in a component library or a fully fledged design system, in order to avoid that people reinvent components that already exist.

## Translations
  I have added translations, rather than hardcoding texts, in order to make it easy to support multiple languages
