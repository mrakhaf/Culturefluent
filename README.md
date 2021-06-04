# Culturefluent
Capstone Project Bangkit 2021

Culturefluent is an application that is used in efforts to preserve Indonesian culture.

This model is to classify traditional musical instruments in Indonesia, we use Tensorflow and VGG16 to make the model.

Using VGG16 model for transfer learning. VGG16 is a convolution neural net (CNN ) architecture which was used to win ILSVR(Imagenet) competition in 2014.  VGG16 focused on having convolution layers of 3x3 filter with a stride 1 and always used same padding and maxpool layer of 2x2 filter of stride 2. We add Deep Neural Network Layers for the last layer with 6 units for the 6 categories that we have and Softmax activation.

data.zip file is a dataset that we have collected from Google Images which contains pictures of traditional Indonesian musical instruments.
We can collect 6 classes consisting of:
  1. Angklung
  2. Suling
  3. Karinding
  4. Kecapi
  5. Rebab
  6. Kendang

The results of the evaluation of the model get 0.84% validation accuracy.
