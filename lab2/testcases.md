### Test unit:
- #### https://www.ebay.com
### Test environment:
- #### any browser

---

## TC01 - Searching for a non-existing item

#### Prerequisites:
- The main page is active
  - `https://www.ebay.com`
- English language is selected

#### TC Scenario:
1) Click on the search field
    - The search field is active
2) Input @string in the search field
    - @string is shown in the search field
3) Click a search button
   - Page with search results is loaded

#### Expected Result: 
- A "No exact matches found" message is shown
- "0 results for @string" message is shown

#### Test Data: 
- @string = "dfsfhjdkalfkdnglf#$!@)(_!"

---

## TC02 - Adding an item into the shopping cart

#### Prerequisites:
- The main page is active
  - `https://www.ebay.com`
- English language is selected

#### TC Scenario:
1) Click on the search field
   - The search field is active
2) Input @string in the search field
   - @string is shown in the search field
3) Click a search button
   - Page with search results is loaded
4) Click on the title of the first item in a list
   - Page with the selected item is loaded
5) Click the "Add to cart" button
   - Shopping cart page is loaded

#### Expected Result: 
- Shopping cart contains item with the title, selected on the step #4

#### Test Data:
- @string = "lego model"

---

## TC03 - Adding an item into the watchlist

#### Prerequisites:
- The main page is active
  - `https://www.ebay.com`
- English language is selected
- The user is authorized

#### TC Scenario:
1) Click on the search field
   - The search field is active
2) Input @string in the search field
   - @string is shown in the search field
3) Click a search button
   - Page with search results is loaded
4) Click on the title of the first item in a list
   - Page with the selected item is loaded
5) Click on the "Add to Watchlist" row
   - "Added to your Watch list" message is shown
   - "Add to Watchlist" row is changed with "Watching" row

#### Expected Result: 
- A watchlist contains the item with the title, selected on the step #4

#### Test Data:
- @string = "big blue spoon"

---

## TC04 - Sorting items

#### Prerequisites:
- The main page is active
  - `https://www.ebay.com`
- English language is selected

#### TC Scenario:
1) Click on the search field
   - The search field is active
2) Input @string in the search field
   - @string is shown in the search field
3) Click a search button
   - Page with search results is loaded
4) Click on the "Best Match" button
   - Dropdown list with options is shown
5) Select "Price + Shipping: highest first" from the list

#### Expected Result:
- The "price" property of each item in the list doesn't exceed <br> 
  the value of the "price" property for the previous item in the list

#### Test Data: 
- @string = "comics"

---

## TC05 - Changing the delivery country

#### Prerequisites:
- The main page is active
  - `https://www.ebay.com`
- English language is selected
- Selected delivery country is "Belarus"

#### TC Scenario:
1) Click a "Ship to" button from the top bar
   - A modal window appeared
   - A dropdown menu button contains title "Belarus"
2) Click a dropdown menu button from the modal window
   - A dropdown list with countries is shown
3) Click "Belgium" from the appeared dropdown list
   - A dropdown list is closed
   - A dropdown menu button contains title "Belgium"
4) Click on the "Done" button
   - A modal window disappeared
5) Click a "Ship to" button from the top bar

#### Expected Result:
- A dropdown menu button in the appeared modal window <br> 
  now contains title "Belgium"

---

## TC06 - Attempting to buy more items than a seller has

#### Prerequisites:
- The main page is active
  - `https://www.ebay.com`
- The shopping cart is empty
- English language is selected
- The user is authorized

#### TC Scenario:
1) Click on the search field
   - The search field is active
2) Input @string in the search field
   - @string is shown in the search field
3) Click a search button
   - Page with search results is loaded
4) Click on the title of the item from the list <br> 
   which contains the "Watch" button and <br>
   a "sold" row contains value > 1000
   - Page with the selected item is loaded
5) Click a "Buy it Now" button
   - Checkout page is loaded
   - Items list contains the item with the title, selected on the step #4
6) Click on the "Quantity" dropdown menu button
   - A dropdown list with options is shown
7) Select "10+" from the menu
   - A dropdown menu became a text field
   - Appeared text field is active
8) Input @number in the active text field 
   - @number is shown in the active text field
9) Click on the "Update" button 

#### Expected Result:
- "The seller doesn't have that many left." message is shown

#### Test Data:
- @number = 999999999
- @string = "pin"

---

## TC07 - Attempting to buy an item without payment credentials

#### Prerequisites:
- The main page is active
  - `https://www.ebay.com`
- The shopping cart is empty
- English language is selected
- The user is authorized

#### TC Scenario:
1) Click on the search field
   - The search field is active
2) Input @string in the search field
   - @string is shown in the search field
3) Click a search button
   - Page with search results is loaded
4) Click on the title of the item from the list <br> 
   which contains the "Watch" button
   - Page with the selected item is loaded
5) Click on the "Buy it Now" button
   - Checkout page is loaded
6) Click on the "Confirm and pay" button

#### Expected Result: 
- "Confirm and pay" button is inactive
- "Select a payment option" message is shown

#### Test Data:
- @string = "pin"

---

## TC08 - Sorting items on a category page

#### Prerequisites:
- The main page is active
  - `https://www.ebay.com`
- English language is selected

#### TC Scenario:
1) Click a "Show by category" button
   - A dropdown menu with options is shown
2) Select "Computers & tablets" in the menu
   - A page with "Computers, Tablets & Network Hardware" title is loaded
3) Click on the "Laptop & Desktop Accessories" title
   - A page with "Laptop & Desktop Accessories" title is loaded
4) Hover a "Sort" dropdown menu button
   - A dropdown list with options is shown
5) Select "Price + Shipping: highest first" from the appeared dropdown list

#### Expected Result:
- Value of the sum of the price and cost properties of each item in the list <br>
  doesn't exceed the same value for the previous item in the list

---

## TC09 - Filtering a search result

#### Prerequisites:
- The main page is active
  - `https://www.ebay.com`
- English language is selected
- Currency is a US dollar

#### TC Scenario
1) Click on the search field
   - The search field is active
2) Input @string in the search field
   - @string is shown in the search field
3) Click a search button
   - Page with search results is loaded
4) Input @low_price into low price filter field
   - @low_price is shown in the low price filter field
5) Input @high_price into high price filter field
   - @high_price is shown in the low price filter field
6) Press "Enter" button on a keyboard

#### Expected Result:
- Value of the property "price" for each item from the list <br>
  is contained in a range from 0 to 5

#### Test Data:
- @string = "pie"
- @low_price = 0
- @high_price = 5

---

## TC10 - Adding a seller to saved

#### Prerequisites:
- The main pase is active
  - `https://www.ebay.com`
- English language is selected
- The user is authenticated
- A "saved" list is empty

#### TC Scenario:
1) Click on the search field
   - The search field is active
2) Input @string in the search field
   - @string is shown in the search field
3) Click a search button
   - Page with search results is loaded
4) Click on the title of the first item from the list
   - Page with the selected item is loaded
5) Click a "Save this Seller" button
   - Button changed title to "Saved"
6) Click a "ebay" icon
   - The main page is loaded
7) Click "Saved" button
   - A "saved" paged is loaded
8) Click a title, which contains the number of sellers
   - A dropdown list is shown

#### Expected Result:
- The appeared dropdown list contains a name of the seller <br>
  of the item, selected on the step #4

#### Test Data:
- @string = "light"
