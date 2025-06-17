UI
I have intentionally been somewhat lax about the details of the UI: colors (I am also color blind :D), spacings, typography. Usually, I would get these values from Figma; otherwise I would query the designer, instead of spending time guessing the values - and risk guessing wrong.  
All values have been created as constants instead of using hardcoded values, which means that they can quickly be changed. 

A couple of things that I would normally ask for clarification about: 
- How should overflowing texts be handled (e.g., fade out or ellipsis)
- Should we make the UI adaptive, such that the number of columns displaying the products decrease for small tablet sizes
- Here, I have used built-in icons from the Material Design library, but I would assume that OnlinePOS has its own icon library, so I would stick to that in a real project in order to avoid inconsistencies in the UI


Translations
  I have added translations, rather than hardcoding texts, in order to make it easy to support multiple languages
