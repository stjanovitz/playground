function [ w e_in ] = logistic_reg( X, Y, max_its )
%LOGISTIC_REG Learn logistic regression model using gradient descent
%   Inputs:
%       X : data matrix
%       Y : data labels (plus or minus 1)
%   Outputs:
%       w : weight vector
%       e_in : in-sample error (as defined in LFD)
w = zeros(size(X,2),1); 
e_in = 0;

step = 0.00001;
gradient_threshold = 0.001;

for t = 1:max_its
    gradient = zeros(1,size(X,2));
    divisor = repmat((1 + exp(bsxfun(@times,Y,X)*w)),1,size(gradient,2));
    gradient = gradient + sum(bsxfun(@times,Y,X) ./ divisor);
    gradient = (-1/size(X,1))*gradient;
    
    if (gradient <= gradient_threshold)
        break;
    end
    w = w - step.*transpose(gradient);
end

e_in = sum(log(1 + exp(bsxfun(@times,Y,X)*w)));
e_in = e_in ./ size(X,1);

end