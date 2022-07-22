Feature: You are working in the backend team that exposes the service https://jsonplaceholder.typicode.com which has the following
  # Make posts: https://jsonplaceholder.typicode.com/posts
  # Comment on posts: https://jsonplaceholder.typicode.com/comments
  # List of users:  https://jsonplaceholder.typicode.com/users

#  @regression
  Scenario Outline: Test that existing comments can be retrieved with a Get request
    Given service is up and running
    When I search for "<id>" of a comment with a Get method
    Then I should get the correct "<id>", "<name>", "<email>" and "<body>" returned with status code of 200
    Examples:
      |id|name                                      |email                  |body                                                                                                                                                                        |
      |2 |quo vero reiciendis velit similique earum |Jayne_Kuhic@sydney.com |est natus enim nihil est dolore omnis voluptatem numquam\net omnis occaecati quod ullam at\nvoluptatem error expedita pariatur\nnihil sint nostrum voluptatem reiciendis et |

  @regression
  Scenario Outline: Test that new comments can be created with a Post request
    Given service is up and running
    When I create a new request for the following details "<postId>", "<name>", "<email>" and "<body>"
    Then I should get the correct "<postId>", "<name>", "<email>" and "<body>" returned and with status code of 201
    Examples:
      |postId|name                     |email             |body                          |
      |8     |My new Comment           |hussaini@gmail.com |I like this new post so much |