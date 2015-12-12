Given(/^Open the homepage for add$/) do
  # pending # Write code here that turns the phrase above into concrete actions
  visit('http://219.244.92.247:8080/BookMark/')
  sleep 1
end

Given(/^Click add button$/) do
  click_on 'addBtn'
  sleep 1
end

Given(/^Input  titleName = "([^"]*)"  and address = "([^"]*)"  And click ok$/) do |title, url|
   fill_in 'titleName' ,with: title
   fill_in 'address' , with: url
   sleep 1
   click_on 'ok'
   sleep 2
end


Given(/^After Add there is (\d+) result$/) do |afertAdd|
  sleep 1
  result = all('.list')
  expect(result.length).to  eq afertAdd.to_i
end


