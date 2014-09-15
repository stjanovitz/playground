%Simple implementation of perceptron learning algorithm on random,
%linearly separable training sets.
%Provides a visualization to see if the number of iterations of the algorithm 
%is less than the theoretical bound. It is.
%Shows that there are many weight vectors that generate the same
%dichotomies, i.e., the hypothesis the algorithm chooses won't necessarily
%be the same as the real classifying function.


iterationsVec = zeros(1,1000);
tBoundDifferencesVec = zeros(1,1000);
foundWeightsEqualRealWeightsVec = zeros(1,1000);

for i = 1:1000
    weights = horzcat([0], rand(1,10));
    realWeights = weights;
    
    trainingSet = horzcat(ones(100,1),(1 - 2.*rand(100,10)));
    trainingSet = horzcat(trainingSet, transpose(sign(weights * transpose(trainingSet))));
    
    %Calculate theoretical bound 
    row = min(weights * transpose(trainingSet(:,1:11)));
    R = max(sqrt(sum(abs(trainingSet(:,1:11)).^2,2)));
    w_star_norm_squared = sum(weights .^ 2);
    t_bound = ((R .^ 2) * w_star_norm_squared) / (row.^2);

    %Initialization
    weights = zeros(1,11);
    iterations = 1;
    classifications = transpose(sign(weights * transpose(trainingSet(:,1:11))));
    misclassifiedIndicies = find(classifications ~= trainingSet(:,12));
    
    while(size(misclassifiedIndicies,1) > 0)
         randomIndex = randsample(misclassifiedIndicies,1);
         randomSampleX = trainingSet(randomIndex,1:11);
         randomSampleY = trainingSet(randomIndex,12);

         weights = weights + randomSampleY .* randomSampleX;

         classifications = transpose(sign(weights * transpose(trainingSet(:,1:11))));
         misclassifiedIndicies = find(classifications ~= trainingSet(:,12));
         iterations = iterations + 1;
    end
    iterationsVec(i) = iterations;
    tBoundDifferencesVec(i) = t_bound-iterations;
    
    foundWeightsEqualRealWeightsVec(i) = (isequal(weights, realWeights));
end

disp(mean(foundWeightsEqualRealWeightsVec));
hist(iterationsVec);
xlabel('Iterations until linear separator is found');
ylabel('Number of times range of iterations occurs');
figure
hist(tBoundDifferencesVec);
xlabel('Difference of tbound and actual number of iterations');
ylabel('Number of times range of differences occurs');