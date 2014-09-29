% max_iter = 1000000;
% trainingData = csvread('cleveland-train.csv',1,0);
% trainingData((trainingData(:,14) == 0),14) = -1;
% 
% numRows = size(trainingData,1);
% %augment with a 1 in the first position in the vector to allow for a 
% %constant/bias term
% X = horzcat(ones(numRows,1),trainingData(:,1:13)); 
% Y = trainingData(:,14);
% [w e_in] = logistic_reg(X,Y,max_iter);
% 
% %Compute classification error on trainingData
% error_training = find_test_error(w, X, Y)
% 
% testData = csvread('cleveland-test.csv',1,0);
% testData((testData(:,14) == 0),14) = -1;
% 
% numRows = size(testData,1);
% X = horzcat(ones(numRows,1),testData(:,1:13)); 
% Y = testData(:,14);
% 
% %Compute classification error on testData
% error_test = find_test_error(w, X, Y)

%Compare to glmfit
% trainingData = csvread('cleveland-test.csv',1,0);
% trainingData((trainingData(:,14) == 0),14) = -1;
% X = trainingData(:,1:13); 
% Y = trainingData(:,14);
% w = glmfit(X,Y);
% 
% X = horzcat(ones(numRows,1),trainingData(:,1:13));
% error_test = find_test_error(w, X, Y)