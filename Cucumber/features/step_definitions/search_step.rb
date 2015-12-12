Given(/^Open the homepage$/) do
  # pending # Write code here that turns the phrase above into concrete actions
  # puts "start"
  visit('http://219.244.92.247:8080/BookMark/')
end

Given(/^Search "([^"]*)"$/) do |arg1|
  sleep 1
  fill_in  'keyword' , with: arg1
  sleep 1
  # pending # Write code here that turns the phrase above into concrete actions
end

Given(/^Have (\d+) result$/) do |arg1|
  sleep 1
  result = all('.list')
  expect(result.length).to  eq arg1.to_i
  # pending # Write code here that turns the phrase above into concrete actions
end
