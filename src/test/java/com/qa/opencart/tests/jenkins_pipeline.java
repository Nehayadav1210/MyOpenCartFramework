pipeline{
	agent any
	
	stages{
		stage("build"){
			steps{
				echo("build the project")
			}
		}
		stage("Run the Unit test"){
			steps{
				echo("run UTs")
			}
		}
		stage("Run the Integration test"){
			steps{
				echo("run ITs")
			}
		}
		stage("Deploy to dev"){
			steps{
				echo("Deploy to dev")
			}
		}
		
		stage("Deploy to QA"){
			steps{
				echo("Deploy to QA")
			}
		}
		stage("Run regression test cases on QA"){
			steps{
				echo("Run test cases on QA")
			}
		}
		stage("Deploy to stage"){
			steps{
				echo("Deploy to stage")
			}
		}
		stage("Run sanity test cases on QA"){
			steps{
				echo("Run sanity test cases on QA")
			}
		}
		stage("Deploy to PROD"){
			steps{
				echo("Deploy to PROD")
			}
		}
		
	}
}