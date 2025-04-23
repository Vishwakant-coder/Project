## Background

I was recently looking for internships on Internshala. At the beginning, I applied to 2-3 internships. Then I saw on Internshala that they say you have to apply to at least 15 internships to get a response from 1 employer. I was like, WTF! Applying for one internship takes me around 5 minutes—from finding the right one to writing a cover letter using ChatGPT, while mentioning my skills, projects, and other things in the cover letter. To make things worse, if there's any assignment in the application process, it takes around 7 to 10 minutes to apply to just one internship. So, I thought, what if I automate everything—from finding internships to updating my resume, writing the perfect cover letter, and even solving assignments—so I could relax and endlessly scroll through Instagram (which shows me depressing content and whose algorithm is getting way too personal day by day).

## Features
* You can apply to a single internship/job in less than 1 minute without touching your keyboard or mouse (or while scrolling Instagram).
* You can theoretically apply to unlimited internships/jobs at once.
* Automatic cover letter writing using ChatGPT and your personal resume, while targeting the internship's description and required skills.
* It will solve assignments that are below the cover letter using ChatGPT, while keeping your resume in mind.
* It will automatically add the required skills (mentioned in the internship/job description) to your Internshala resume.
* It checks if the assignment is easy enough to be solved by ChatGPT. If it asks for any personal info, it skips that internship and saves the cover letter so you can easily apply manually.
* Generates **beautiful**(like her) success/failed reports after applying to internships, so you can keep track of every application.
* Detects fake internships/jobs and filters them out. It automatically filters internships/jobs if any employer hasn't hired a single candidate till now and is just posting fake internships. It checks for employers' hiring and posting stats and their active history on Internshala.

## How to Use

#### Adding your resume into the bot:
1. Run the module using `python3 -m internshala_bot` in your terminal.
2. This will create a local file named `resume.ini`.
3. Open this file using any text editor and edit the `Skills`, `Certificates`, and `Projects` sections accordingly. Then save it.

#### Where to find the internship/Job URL
1. Open any browser and use Incognito Mode (Don't use normal mode, otherwise, you won't be able to copy the internship URL).
2. Go to [https://internshala.com/internships/](https://internshala.com/internships/) or For Jobs GO to [https://internshala.com/jobs/](https://internshala.com/jobs/)
3. Apply your desired filters such as Profile, Location, Work from Home, Stipend, etc.

4. Now, if you want to apply to multiple internships/jobs at once, then after applying filters:
    * Go to the address bar of the browser and copy the URL.

5. Now, if you want to apply to a specific single internship/job, then after applying the filters:
    * Click on any internship/job you want.
    * It will open in a new tab.
    * Go to that tab and copy the URL from the address bar.

#### Final Steps
1. Run the module using the terminal and paste the copied URL from the previous step, such as:    
    `python3 -m internshala_bot copied_internship_url`    
    `python3 -m internshala_bot copied_job_url`    
    
    Example:  
```    
    python3 -m internshala_bot https://internshala.com/internship/detail/work-from-home-part-time-teaching-assistant-data-science-internship-at-internshala1723621737
```

2. Now follow the instructions printed in the terminal.
    - It will ask you to sign in to your Internshala and ChatGPT accounts.
    - You only need to do this once; next time, it will save the session.

* If you want to apply to multiple internships/jobs, enter the URL copied from step 4.    
    Example:    
        `python3 -m internshala_bot https://internshala.com/internships/work-from-home-data-science-internships/`  

    This will apply to all work-from-home Data Science internships, put jobs url for jobs.    

* If you want to apply to 2, 3, or 5 individual internships:
    - Copy all the individual internship/job URLs, then enter them one by one.    

    `python3 -m internshala_bot internship_one_url internship_two_url internship_three_url`    
    `python3 -m internshala_bot job_one_url job_two_url job_three_url`    

    In this way, you can add multiple URLs separated by spaces.

Example:
```    
    python3 -m internshala_bot https://internshala.com/internship/detail/work-from-home-part-time-teaching-assistant-data-science-internship-at-internshala1723621737 https://internshala.com/internship/detail/acquisition-ninja-internship-in-multiple-locations-at-zomato1721285327 https://internshala.com/internship/detail/work-from-home-part-time-teaching-assistant-data-science-internship-at-internshala1723621737
```

##### After running successfully, it will generate reports in the `reports/` folder in the current directory. There are two types of reports: 
    - Application Success Report
    - Application Failed Report

* The Application Failed Report contains a link to the internship, the cover letter generated by AI, and the reason why it failed to apply so you can copy the cover letter and apply manually.
