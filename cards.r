library(tidyverse)
library(jsonlite)

parsed_data <- fromJSON("C:/Users/sacha/Downloads/cards_2020_11_14.json")

reshaped_list <- lapply(parsed_data, function(x) unlist(x))

df <- as.data.frame(do.call(rbind, reshaped_list))

head(df)

silent <- filter(df, color == "GREEN")

silent %>% count(rarity)
