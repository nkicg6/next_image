(ns next-image.gui
  (:require [seesaw.core :as seesaw]
            [seesaw.chooser :as chooser]
            [seesaw.font :as font]
            [next-image.file-utils :refer [get-files!]]))

(def app-state (atom ""))

(defn update-listbox
  [frame fend]
  (seesaw/config! frame :content
                  (seesaw/border-panel :north (seesaw/scrollable
                                               (seesaw/listbox :model (get-files! @app-state fend)))
                                       :south (seesaw/left-right-split (seesaw/label :text "metadata here") (seesaw/button :text "choose a different project" :listen [:action #(chooser-dialogue % base-frame ".txt")]))
                                       :hgap 5 :vgap 10 :border 10)))

(defn chooser-dialogue [_ frame fend]
  (->> (chooser/choose-file :dir "~" :type :open :selection-mode :dirs-only)
       (reset! app-state))
  (update-listbox frame fend))


(def base-frame
  (seesaw/frame
   :id :base-frame
   :title "next_image v0.1-dev"
   :width 400 :height 400
   :content
   (seesaw/border-panel
    :center
    (seesaw/label :text "<html>Welcome to <i>next image</i>!<br></br>Select the folder containing the images you are analyzing below to get started"
                  :font "ARIAL-21"
                  :h-text-position :center)
    :south
    (seesaw/button :text "Select your image folder"
                   :id :select-dirs
                   :listen [:action #(chooser-dialogue % base-frame ".txt")])
    :hgap 5 :vgap 10 :border 10)))

;; to update and quickly check the layout:
#_(update-listbox base-frame ".txt")


(-> base-frame seesaw/show!)

(defn rm []
  (seesaw/dispose! base-frame))

@app-state


