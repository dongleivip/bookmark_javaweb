Given(/^Open the homepage for delete$/) do
  @page = Page.new
  @page.load
end

Given(/^Search "([^"]*)" Items$/) do |arg1|
  @page.search arg1
end


Given(/^Get (\d+) results$/) do |arg1|
  expect(@page.result).to eq arg1.to_i
end

Given(/^Delete  "([^"]*)" Item$/) do |arg1|
  click_on arg1
  sleep 1
  click_on 'ok'
  sleep 2
end


Given(/^Get (\d+) Items Left$/) do |arg1|
  sleep 1
   expect(@page.result).to eq arg1.to_i
end


