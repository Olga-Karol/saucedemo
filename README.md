# Page object practice. Saucedemo lab.

Description


## Checklist

| Module/Page                     | Test                                                           |
|---------------------------------|----------------------------------------------------------------|
| Login form                      | Log in with valid username/password                            |
 | Login form                      | Login with invalid username/password                           |
| Login form                      | User with invalid username cannot log in                       |
| Login form                      | User with invalid password cannot log in                       |
| Login form                      | Username is required input                                     |
| Login form                      | Password is required input                                     |
| Login form                      | Check placeholder for username                                 |
| Login form                      | Check placeholder for password                                 |
| Login form                      | Form is unavailable for logged user                            |
| Catalog page                    | Log out                                                        |
| Catalog page                    | Add product to cart from catalog                               |
| Catalog page                    | Open product page                                              |
| Catalog page                    | Menu icon is available                                         |
| Catalog page                    | Menu opens                                                     |
| Catalog page                    | 'All items' link redirects to Catalog                          |
| Catalog page                    | 'About' link opens external link (sauselabs.com)               |
| Catalog page                    | 'Reset App State' link clears cart                             |
| Catalog page                    | 'Product sort' dropdown is available on page                   |
| Catalog page                    | Default sor option is 'NAME A to Z'                            |
| Catalog page                    | Products are sorted by 'NAME A to Z'                           |
| Catalog page                    | Products are sorted by 'NAME Z to A'                           |
| Catalog page                    | Products are sorted by 'Price low to high'                     |
| Catalog page                    | Products are sorted by 'Price high to low'                     |
| Catalog page                    | Counter on the 'Cart' icon appears after product adding        |
| Catalog page                    | Correct value is shown in the cart counter                     |
| Product page                    | Product attributes are matching to product data in catalog     |
| Product page                    | Add product to cart from product page                          |
| Product page                    | 'Back to Products' button redirects to Catalog page            |
| Cart page                       | Remove product in the Cart                                     |
| Cart page                       | 'Continue shopping' button redirects to Catalog page           |
| Cart page                       | 'Checkout' button redirects to Checkout: Your Information page |
| Checkout Your Information page  | Post user valid data                                           |
| Checkout: Your Information page | Check placeholders for FirstName/LastName/PostalCode           |
| Checkout: Your Information page | FirstName is required                                          |
| Checkout: Your Information page | LastName is required                                           |
| Checkout: Your Information page | PostalCode is required                                         |
| Checkout: Overview page         | Added product to Cart is shown on Overview page                |
| Checkout: Overview page         | 'Finish' button redirects to Checkout: Complete! page          |

## Getting Started
These short instructions will get you commands that help you to update dependencies to the latest version, run tests.

#### 1. For checking for new dependency updates use the next command:
mvn versions:display-dependency-updates
#### 2. For searching the pom for all versions which have been a newer version and replacing them with the latest version:
mvn versions:use-latest-versions
#### 3. Run tests without specific parameters use the command:
mvn clean test

**Notes:** command should run in the project directory

#### 4. Run definite test suit:

mvn clean test -Dsuit=SmokeSuite

#### 5. Run definite test class(es), for example ProductTest, OrderProductTest:

mvn clean test -Dtest=*ProductTest

#### 6. Run definite method(s) in a single test class, for example for LoginTest (validCredentialsLoginTest + invalidLoginTest + placeholderTest):

mvn clean test -Dtest=LoginTest#*LoginTest+placeholderTest




